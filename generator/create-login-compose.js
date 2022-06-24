'use strict';
// chosee project
// edit manifest
// add res
// add java
import { CCLI_PROMPTS, CCLI_PROMPT_TYPE } from "../util/prompts.js"
import { constants } from '../util/constants.js'
import fs from 'fs';


export const creatLoginCompose =  {
    description: 'Login page to exisiting application',
    prompts: [
        {
            type: CCLI_PROMPT_TYPE.fileDir,
            onlyShowDir: true,
            root: "projects/",
            name: 'selectedProjectDir',
            message: 'Select application to add login ? ',
            validate: function(source) {
                let configLoc = `${source}/.ccli_config.json`
                if(!fs.existsSync(configLoc)) {
                    return "Please select valid project created by ccli"
                }
                return true
            }
        },
        {
            type: CCLI_PROMPT_TYPE.fileDir,
            onlyShowDir: true,
            root: constants.loginTemplatePath,
            name: 'selectedLoginTemplate',
            message: 'Choose login template -> '
        },
        {
            type: 'input',
            name: 'loginPageName',
            default: "LoginPage",
            message: 'Enter name for compose login component:'
        },
        {
            type: 'confirm',
            name: 'addGoogleService',
            default: false,
            message: 'Do you want to add google service ? '
        },
        {
            when: function (response) {
                return response.addGoogleService
            },
            type: CCLI_PROMPT_TYPE.fileDir,
            name: "googleServiceAssest",
            enableGoUpperDirectory: true,
            validate: function(file) {
                if (file.endsWith(".json"))
                    return true
                return "Please select json file"
            },
            message: 'Select google-service.json file '
        },
        {
            when: function (response) {
                return response.addGoogleService
            },
            type: 'confirm',
            name: 'addFirebase',
            message: 'Do you want to add firebase ? '
        },{
            type: 'confirm',
            name: 'addRetrofit',
            message: 'Do you want to add retrofit ? '
        },{
            type: 'input',
            when: function (response) {
                return response.addRetrofit
            },
            name: 'baseUrl',
            message: 'Enter base url: '
        },
    ],
    actions: function (data) {
        
        let actions = [];
        let packageLoc = data.packageId.split(".").join("/")
        
        actions.push(
            { 
                type: "addMany",
                destination: `${constants.outputPath}${data.appName}`,
                base: `${constants.basePath}`,
                templateFiles: `${constants.basePath}/**/*`
            },
            { 
                type: "addMany",
                base: `${constants.setupPath}`,
                destination: `${constants.outputPath}${data.appName}/${constants.javaPath}${packageLoc}`,
                templateFiles: `${constants.setupPath}/**/*`
            }
        )
        if (data.addGoogleService) {
            actions.push({
                type: "copyAssets",
                fileType: "googleServiceAssest"
            })
        }
        return actions;
    }
}