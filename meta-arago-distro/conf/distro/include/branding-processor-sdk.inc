ARAGO_TISDK_IMAGE = "processor-sdk-linux-image"

# Set CMEM reserved memory region
RESERVE_CMEM = "${@bb.utils.contains('MACHINE_FEATURES','dsp','1','0',d)}"
CMEM_BASE:omap-a15 ?= "a0000000"
CMEM_SIZE:omap-a15 ?= "08000000"

# To create newlines in the message \n is used and the slash must be escaped
# to function properly

SRCIPK_GIT_COMMIT_MESSAGE = "\
Create local branch\\n\
\\n\
The below commit is used for this local branch and is the one used by\\n\
this sdk:\\n\
${SRCREV}."

SRCIPK_SHALLOW_GIT_COMMIT_MESSAGE ="${SRCIPK_GIT_COMMIT_MESSAGE}\\n\\n\
To reduce the size of the SDK installer only a portion of the git commit\\n\
history has been preserved. Also links to remote branches and tags do\\n\
not exist in this copy of the repository.\\n\
To fix this please run the unshallow-repositories.sh\\n\
script located in the sdk\'s bin directory\\n\
\\n\
The script may take several minutes but you will then have a full copy of\\n\
the git repository including its entire git history.\\n"

SRCIPK_CUSTOM_GIT_BRANCH:pn-linux-ti-staging = "processor-sdk-local"
SRCIPK_CUSTOM_GIT_MESSAGE:pn-linux-ti-staging = "${SRCIPK_SHALLOW_GIT_COMMIT_MESSAGE}"
SRCIPK_SHALLOW_CLONE:pn-linux-ti-staging = "true"
SRCIPK_SHALLOW_DEPTH:pn-linux-ti-staging = "400"

SRCIPK_CUSTOM_GIT_BRANCH:pn-u-boot-ti-staging = "processor-sdk-local"
SRCIPK_CUSTOM_GIT_MESSAGE:pn-u-boot-ti-staging = "${SRCIPK_GIT_COMMIT_MESSAGE}"
