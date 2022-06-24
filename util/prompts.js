export const CCLI_PROMPT_TYPE = {
    fileDir: "fileDir"
}

export const CCLI_PROMPTS = {
    CHOOSE_PROEJECT: {
        type: CCLI_PROMPT_TYPE.fileDir,
        onlyShowDir: true,
        root: "projects/",
        name: 'selectedProjectDir',
        message: 'Select application to add login ? '
    },
    CHOOSE_FILE: {
        type: CCLI_PROMPT_TYPE.fileDir,
        name: 'selectedProjectFile'
    }
}