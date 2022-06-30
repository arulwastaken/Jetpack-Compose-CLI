# Jetpack compose cli for Android Project

A Boilerplate code generator for creating Android project. Using fully Kotlin and MVVM pattern that refer to Android Jetpack. Because Im tired to setup Dagger and other stuffs everytime initializing a new project.

[![npm version](https://badge.fury.io/js/jetpack-compose-cli.svg)](https://badge.fury.io/js/jetpack-compose-cli)

## What's Included:

- [Kotlin](https://kotlinlang.org/)
- [MVI](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)
- [View Binding](https://developer.android.com/topic/libraries/view-binding)
- [Hilt 2](https://github.com/google/dagger)
- [Retrofit 2](https://github.com/square/retrofit)
- [Glide](https://github.com/bumptech/glide)


## TODO List:

- [x] Offline First (using Room and paging lib)
- [x] Add Example Usage of Fragment
- [ ] Create Documentation Code Snippet
- [ ] Add example for using Coroutines
- [ ] Create Unit Test


## How To Use

Create your project directory

```bash
mkdir MyNewApp
cd MyNewApp/
```

Install generator using NPM

```bash
npm install -g jetpack-compose-cli
```

Run Generator using Yeoman

```bash
jetpack-compose-cli create-compose-app
```

Open project using Android Studio, build, and done!


## License

Copyright (c) 2022 Arul Mani

This software is published under MIT License. See [LICENSE.md](LICENSE.md) for more details.