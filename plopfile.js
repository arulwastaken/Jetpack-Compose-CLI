
import copyDir from 'copy-dir';
import fs from 'fs';
import fuzzyPath from 'inquirer-file-tree-selection-prompt';
import {constants} from './util/constants.js'

import { creatComposeApp } from './generator/create-compose-app.js';
import { creatLoginCompose } from './generator/create-login-compose.js';


export default function (plop) {


    plop.setWelcomeMessage(`
    Jetpack Compose Cli
    
    Usage
        $ jetpack-compose-cli <cmd>
    
    Examples
	    $ jetpack-compose-cli create-compose-app
	    $ jetpack-compose-cli create-login-module
`)
    plop.setGenerator('create-compose-app', creatComposeApp);
    plop.setGenerator('create-compose-login', creatLoginCompose);

    plop.setActionType('copyAssets', function (answers, config, plop) {
        let src = answers[config.fileType]
        let outputPath = config.outputPath
        switch(config.fileType) {
            case 'googleServiceAssest':
                outputPath += "/app/google-services.json"
                break;
        } 
        fs.copyFileSync(src, outputPath);
        return 'file moved';
    });

    plop.setPrompt('fileDir', fuzzyPath);
    plop.setHelper('upperCase', (txt) => txt.toUpperCase());
    plop.setHelper('currentDate', (txt) => new Date().toISOString());
    plop.setHelper('ccliVersion', (txt) => "0.0.1-dev");
};