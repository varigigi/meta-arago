SUMMARY = "Arago feed configuration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r1"
PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_DEFAULT_DEPS = "1"

FEEDNAMEPREFIX ?= ""
FEEDURIPREFIX ?= "feeds/"
DISTRO_FEED_URI ?= ""

do_compile() {
	mkdir -p ${S}/${sysconfdir}/opkg/

	archconf=${S}/${sysconfdir}/opkg/arch.conf

	rm -f $archconf
	ipkgarchs="${ALL_MULTILIB_PACKAGE_ARCHS}"
	priority=1
	for arch in $ipkgarchs; do
		echo "arch $arch $priority" >> $archconf
		priority=$(expr $priority + 5)
	done

	basefeedconf=${S}/${sysconfdir}/opkg/base-feeds.conf

	rm -f $basefeedconf
	touch $basefeedconf

	for arch in $ipkgarchs; do
		echo "src/gz ${FEEDNAMEPREFIX}$arch ${DISTRO_FEED_URI}/${FEEDURIPREFIX}$arch" >> $basefeedconf
	done
}

do_install () {
	install -d ${D}${sysconfdir}/opkg
	install -m 0644  ${S}/${sysconfdir}/opkg/* ${D}${sysconfdir}/opkg/
}

FILES:${PN} = "${sysconfdir}/opkg/"

CONFFILES:${PN} += "${sysconfdir}/opkg/base-feeds.conf \
                    ${sysconfdir}/opkg/arch.conf"
