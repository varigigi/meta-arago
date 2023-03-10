ARAGO_TISDK_IMAGE = "arago-amsdk-image"

PREFERRED_PROVIDER_virtual/kernel:am335x-evm = "sitara-linux-ti-staging"
PREFERRED_PROVIDER_virtual/kernel:am437x-evm = "sitara-linux-ti-staging"
PREFERRED_VERSION_sitara-linux-ti-staging = "3.14%"

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

SRCIPK_CUSTOM_GIT_BRANCH:pn-sitara-linux-ti-staging = "amsdk-8.0"
SRCIPK_CUSTOM_GIT_MESSAGE:pn-sitara-linux-ti-staging = "${SRCIPK_SHALLOW_GIT_COMMIT_MESSAGE}"

SRCIPK_CUSTOM_GIT_BRANCH:pn-u-boot-ti-staging = "amsdk-8.0"
SRCIPK_CUSTOM_GIT_MESSAGE:pn-u-boot-ti-staging = "${SRCIPK_GIT_COMMIT_MESSAGE}"
