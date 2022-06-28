#!/usr/bin/env node

import meow from 'meow';
import foo from './lib/index.js';

const cli = meow(`
	Usage
	  $ jetpack-compose-cli <input>

	Options
	  --rainbow, -r  Include a rainbow

	Examples
	  $ jetpack-compose-cli 
      $ jetpack-compose-cli create-compose-app
      $ jetpack-compose-cli create-login-compose
	  🌈 unicorns 🌈
`, {
	importMeta: import.meta
});
