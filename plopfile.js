
import copyDir from 'copy-dir';
import fs from 'fs';
import fuzzyPath from 'inquirer-file-tree-selection-prompt';
import {constants} from './util/constants.js'

import { creatComposeApp } from './generator/create-compose-app.js';
import { creatLoginCompose } from './generator/create-login-compose.js';


export default function (plop) {


    plop.setWelcomeMessage(`Usage
        $ jetpack-compose-cli <cmd>
    
    Examples
	    $ jetpack-compose-cli create-compose-app
	    $ jetpack-compose-cli create-login-module
`)
    plop.setGenerator('create-compose-app', creatComposeApp);
    plop.setGenerator('create-compose-login', creatLoginCompose);
    
    plop.setActionType('copyBase', function (answers, config, plop) {
        copyDir.sync('base-templates', `${constants.outputPath}${answers.appName}`)
        return 'success status message';
    });

    plop.setActionType('copyAssets', function (answers, config, plop) {
        let src = answers[config.fileType]
        var dest = `${constants.outputPath}${answers.appName}/`
        switch(config.fileType) {
            case 'googleServiceAssest':
                dest += "app/google-services.json"
                break;
        } 
        fs.copyFileSync(src, dest);
        return 'file moved';
    });

    plop.setPrompt('fileDir', fuzzyPath);
    plop.setHelper('upperCase', (txt) => txt.toUpperCase());
    plop.setHelper('currentDate', (txt) => new Date().toISOString());
    plop.setHelper('ccliVersion', (txt) => "0.0.1-dev");
};