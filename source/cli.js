#!/usr/bin/env node

import meow from 'meow';

const cli = meow(`
	Usage
	  $ jetpack-compose-cli <input>

	Options
	  --default creat default settings

	Examples
	  $ jetpack-compose-cli 
      $ jetpack-compose-cli create-compose-app
      $ jetpack-compose-cli create-login-compose
	  🌈 unicorns 🌈
`, {
	importMeta: import.meta,
    flags: {
		default: {
			type: 'boolean',
			default: true,
			alias: 'r'
		}
    }
});

console.log("input",cli.input[0], cli.flags);