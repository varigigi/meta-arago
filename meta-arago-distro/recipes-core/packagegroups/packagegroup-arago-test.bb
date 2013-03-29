DESCRIPTION = "Extended task to get System Test specific apps"
LICENSE = "MIT"
PR = "r3"

inherit packagegroup

ARAGO_TEST = "\
    bonnie++ \
    hdparm \
    iozone3 \
    iperf \
    lmbench \
    rt-tests \
    evtest \
    bc \
    "

ARAGO_TI_TEST = "\
    ltp-ddt \
    "

ARAGO_TI_TEST_append_omap-a15 = " \
    omapconf \
    "

RDEPENDS_${PN} = "\
    ${ARAGO_TEST} \
    ${ARAGO_TI_TEST} \
    "
