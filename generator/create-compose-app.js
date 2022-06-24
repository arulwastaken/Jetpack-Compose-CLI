import { CCLI_PROMPT_TYPE } from "../util/prompts.js"
import { constants } from '../util/constants.js'

let databaseChoices = [
    "Realm", "Room", "Sqlite", "None"
]
let networkingChoices = [
    "Retrofit", "Volley", "None"
]

export const creatComposeApp =  {
    description: 'Create anadroid application using compose architecture',
    prompts: [
        {
            type: 'input',
            name: 'appName',
            message: 'Enter application name: '
        },
        {
            type: 'input',
            name: 'packageId',
            default: 'com.example.firsttep',
            message: 'Enter package Id: '
        },
        {
            type: 'input',
            name: 'applicationId',
            default: 'com.example.firsttep',
            message: 'Enter application Id: '
        },
        {
            type: 'input',
            name: 'appVersion',
            default: "1.0.0",
            message: 'Enter application version Id: '
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
            default: false,
            message: 'Do you want to add firebase ? '
        },
        {
            type: 'list',
            name: 'networking',
            choices: networkingChoices,
            default: networkingChoices.length - 1,
            message: 'Choose networking lib : '
        }, {
            when: function (response) {
                return response.networking != "None"
            },
            type: 'input',
            name: 'baseUrl',
            default: "https://192.168.1.12:8080",
            message: 'Enter Base URL for networking : '
        },
        {
            type: 'list',
            name: 'database',
            choices: databaseChoices,
            default: databaseChoices.length - 1,
            message: 'Choose database lib : '
        },
    ],
    actions: function (data) {
        console.log("Processing", data)
        
        let actions = [];
        let packageLoc = data.packageId.split(".").join("/")
        let projectPath = `${constants.outputPath}${data.appName}`
        let projectJavaPath = `${projectPath}/${constants.javaPath}${packageLoc}` 
        let addDatabase = data.database != "None"
        let addNetworking = data.networking != "None" 

        
        actions.push(
            { 
                type: "addMany",
                destination: `${projectPath}`,
                base: `${constants.basePath}`,
                templateFiles: `${constants.basePath}/**/*`
            },
            { 
                type: "addMany",
                base: `${constants.setupPath}`,
                destination: `${projectJavaPath}`,
                data: {
                    addDatabase,
                    addNetworking,
                    addRealm: data.database == "Realm",
                    addRoom: data.database == "Room",
                    addSqlite: data.database == "Sqlite",
                    addRetrofit: data.networking == "Retrofit",
                    addVolley: data.networking == "Volley"
                },
                templateFiles: `${constants.setupPath}/**/*`
            }
        )
        if (data.addGoogleService) {
            actions.push({
                type: "copyAssets",
                fileType: "googleServiceAssest"
            })
        }
        
        actions.push(
            {
                type: "add",
                path: `${projectPath}/.ccli_config.json`,
                templateFile: constants.ccliConfigPath
            }
        )
        
        if (addDatabase) {
            let dbLoc = `${constants.databaseTemplatePath}/${data.database.toLowerCase()}`
            actions.push({ 
                type: "addMany",
                base: dbLoc,
                destination: `${projectJavaPath}/db`,
                templateFiles: `${dbLoc}/**/*`
            })
        }

        if (addNetworking) {
            let netLoc = `${constants.networkingTemplatePath}/${data.networking.toLowerCase()}`
            actions.push({ 
                type: "addMany",
                base: netLoc,
                destination: `${projectJavaPath}/data/remote`,
                templateFiles: `${netLoc}/**/*`
            })
        }
        
        return actions;
    }
}