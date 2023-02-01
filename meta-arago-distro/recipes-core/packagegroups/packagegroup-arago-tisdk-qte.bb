DESCRIPTION = "Task to add Qt embedded related packages"
LICENSE = "MIT"
PR = "r22"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

#    qtdeclarative-examples
RDEPENDS:${PN} = "\
    packagegroup-arago-qte \
    qtbase-examples \
    qtdeclarative-tools \
    qtlocation-examples \
    qtmultimedia-examples \
    qtscript-examples \
    qtsvg-examples \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland-examples', '', d)} \
    qtserialport-examples \
    qtcharts-examples \
    qt-tstat \
"
