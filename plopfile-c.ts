import {NodePlopAPI} from 'plop';

export default function (plop: NodePlopAPI) {

    var templatePath = 'plop-templates/'
    var copydir = require('copy-dir')
    // plop generator code
    plop.setGenerator('compose-base', {
        description: 'this is a skeleton plopfile',
        prompts: [
            {
                type: 'input',
                name: 'appName',
                message: 'Enter application name: '
            },
            {
                type: 'input',
                name: 'packageId',
                message: 'Enter package Id: ',
                prefix: 'com.'
            },
            {
                type: 'input',
                name: 'applicationId',
                prefix: 'com.',
                message: 'Enter application Id: '
            },
            {
                type: 'input',
                name: 'appVersion',
                default: "1.0.0",
                message: 'Enter application version Id: '
            }
        ],
        actions: function (data) {
            console.log("Dataaa", data)
            let actions = [];
            let packageLoc = data.packageId.split(".").join("/")
            console.log("Package Loc", packageLoc)
            actions.push(
                {
                    type: "copyBase",
                    configProp: 'available from the config param'
                }
            )

            return actions;
        }
    });

    plop.setActionType('copyBase', function (answers, config, plop) {
        console.log("Copy base... >",answers, config)
        // do something
        // doSomething(config.configProp);
        // if something went wrong
        // throw 'error message';
        // otherwise
        let packageLoc = answers.packageId.split(".").join("/")
        copydir.sync('plop-templates/Compose_Base/base', `output/${answers.appName}`)
        return 'success status message';
    });

    // or do async things inside of an action
    // plop.setActionType('copyBase', function (answers, config, plop) {
    //     // do something
    //     return new Promise((resolve, reject) => {
    //         if (true) {

    //             resolve('success status message');
    //         } else {
    //             reject('error message');
    //         }
    //     });
    // });


    plop.setHelper('upperCase', function (text) {
        return text.toUpperCase();
    });

    // or in es6/es2015
    plop.setHelper('upperCase', (txt) => txt.toUpperCase());
};