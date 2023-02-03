inherit cross-canadian

require external-arm-bfd-version.inc

PR = "r9"

PN = "external-arm-sdk-toolchain-${TARGET_ARCH}"
BPN = "external-arm-sdk-toolchain"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_DEFAULT_DEPS = "1"
EXCLUDE_FROM_SHLIBS = "1"

# License applies to this recipe code, not the toolchain itself
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

INSANE_SKIP_gcc-cross-canadian-${TRANSLATED_TARGET_ARCH} = "dev-so staticdev file-rdeps"
INSANE_SKIP_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH} = "dev-so file-rdeps"
INSANE_SKIP_binutils-cross-canadian-${TRANSLATED_TARGET_ARCH} = "dev-so file-rdeps"

PROVIDES = "\
	gcc-cross-canadian-${TRANSLATED_TARGET_ARCH} \
	${@oe.utils.conditional('PREFERRED_PROVIDER_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH}', 'external-arm-sdk-toolchain', 'gdb-cross-canadian-${TRANSLATED_TARGET_ARCH}', '', d)} \
	binutils-cross-canadian-${TRANSLATED_TARGET_ARCH} \
"

PACKAGES = "\
	gcc-cross-canadian-${TRANSLATED_TARGET_ARCH} \
	${@oe.utils.conditional('PREFERRED_PROVIDER_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH}', 'external-arm-sdk-toolchain', 'gdb-cross-canadian-${TRANSLATED_TARGET_ARCH}', '', d)} \
	binutils-cross-canadian-${TRANSLATED_TARGET_ARCH} \
"

# Don't need the extra target triplet in the new SDK dir structure
bindir = "${exec_prefix}/bin"
libdir = "${exec_prefix}/lib"
libexecdir = "${exec_prefix}/libexec"
datadir = "${exec_prefix}/share"
gcclibdir = "${libdir}/gcc"

FILES_gcc-cross-canadian-${TRANSLATED_TARGET_ARCH} = "\
	${prefix}/${EAT_TARGET_SYS}/lib/libstdc++.* \
	${prefix}/${EAT_TARGET_SYS}/lib/libgcc_s.* \
	${prefix}/${EAT_TARGET_SYS}/lib/libsupc++.* \
	${gcclibdir}/${EAT_TARGET_SYS}/${EAT_VER_GCC}/* \
	${bindir}/${TARGET_PREFIX}gcov \
	${bindir}/${TARGET_PREFIX}gcc* \
	${bindir}/${TARGET_PREFIX}g++ \
	${bindir}/${TARGET_PREFIX}cpp \
	${libexecdir}/* \
"

FILES_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH} = "\
	${bindir}/${TARGET_PREFIX}gdb \
	${bindir}/${TARGET_PREFIX}gdbtui \
	${datadir}/gdb/* \
	${datadir}/info/* \
	${datadir}/man/man1/${TARGET_PREFIX}* \
"

FILES_binutils-cross-canadian-${TRANSLATED_TARGET_ARCH} = "\
	${prefix}/${EAT_TARGET_SYS}/bin/ld* \
	${prefix}/${EAT_TARGET_SYS}/bin/objcopy \
	${prefix}/${EAT_TARGET_SYS}/bin/strip \
	${prefix}/${EAT_TARGET_SYS}/bin/nm \
	${prefix}/${EAT_TARGET_SYS}/bin/ranlib \
	${prefix}/${EAT_TARGET_SYS}/bin/as \
	${prefix}/${EAT_TARGET_SYS}/bin/ar \
	${prefix}/${EAT_TARGET_SYS}/bin/objdump \
	${prefix}/${EAT_TARGET_SYS}/lib/ldscripts/* \
	${bindir}/${TARGET_PREFIX}ld* \
	${bindir}/${TARGET_PREFIX}addr2line \
	${bindir}/${TARGET_PREFIX}objcopy \
	${bindir}/${TARGET_PREFIX}readelf \
	${bindir}/${TARGET_PREFIX}strip \
	${bindir}/${TARGET_PREFIX}nm \
	${bindir}/${TARGET_PREFIX}ranlib \
	${bindir}/${TARGET_PREFIX}gprof \
	${bindir}/${TARGET_PREFIX}as \
	${bindir}/${TARGET_PREFIX}c++filt \
	${bindir}/${TARGET_PREFIX}ar \
	${bindir}/${TARGET_PREFIX}strings \
	${bindir}/${TARGET_PREFIX}objdump \
	${bindir}/${TARGET_PREFIX}size \
"

DESCRIPTION_gcc-cross-canadian-${TRANSLATED_TARGET_ARCH} = "The GNU cc and gcc C compilers"
DESCRIPTION_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH} = "gdb - GNU debugger"
DESCRIPTION_binutils-cross-canadian-${TRANSLATED_TARGET_ARCH} = "A GNU collection of binary utilities"

#LICENSE = "${ARG_LIC_LIBC}"
#LICENSE_gcc-cross-canadian-${TRANSLATED_TARGET_ARCH} = "${ARG_LIC_GCC}"
#LICENSE_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH} = "${ARG_LIC_GDB}"
#LICENSE_binutils-cross-canadian-${TRANSLATED_TARGET_ARCH} = "${ARG_LIC_BFD}"

PKGV = "${EAT_VER_MAIN}"
PKGV_gcc-cross-canadian-${TRANSLATED_TARGET_ARCH} = "${EAT_VER_GCC}"
PKGV_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH} = "${EAT_VER_GDB}"
PKGV_binutils-cross-canadian-${TRANSLATED_TARGET_ARCH} = "${EAT_VER_BFD}"

LIBDIR = "lib"
LIBDIR_aarch64 = "lib64"

do_install() {
	install -d ${D}${prefix}/${EAT_TARGET_SYS}/bin
	install -d ${D}${prefix}/${EAT_TARGET_SYS}/lib
	install -d ${D}${bindir}
	install -d ${D}${libdir}
	install -d ${D}${prefix}/${EAT_TARGET_SYS}/lib/ldscripts
	install -d ${D}${libexecdir}
	${@oe.utils.conditional('PREFERRED_PROVIDER_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH}', 'external-arm-sdk-toolchain', 'install -d ${D}${datadir}/gdb', '', d)}
	${@oe.utils.conditional('PREFERRED_PROVIDER_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH}', 'external-arm-sdk-toolchain', 'install -d ${D}${datadir}/info', '', d)}
	${@oe.utils.conditional('PREFERRED_PROVIDER_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH}', 'external-arm-sdk-toolchain', 'install -d ${D}${datadir}/man/man1', '', d)}
	install -d ${D}${gcclibdir}/${EAT_TARGET_SYS}/${EAT_VER_GCC}/include

	cp -a ${TOOLCHAIN_PATH}/${EAT_TARGET_SYS}/${LIBDIR}/libstdc++.* ${D}${prefix}/${EAT_TARGET_SYS}/lib
	cp -a ${TOOLCHAIN_PATH}/${EAT_TARGET_SYS}/${LIBDIR}/libgcc_s.* ${D}${prefix}/${EAT_TARGET_SYS}/lib
	cp -a ${TOOLCHAIN_PATH}/${EAT_TARGET_SYS}/${LIBDIR}/libsupc++.* ${D}${prefix}/${EAT_TARGET_SYS}/lib
	cp -a ${TOOLCHAIN_PATH}/lib/gcc/${EAT_TARGET_SYS}/${EAT_VER_GCC}/* ${D}${gcclibdir}/${EAT_TARGET_SYS}/${EAT_VER_GCC}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}gcov ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}gcc* ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}g++ ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}cpp ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/libexec/* ${D}${libexecdir}

	${@oe.utils.conditional('PREFERRED_PROVIDER_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH}', 'external-arm-sdk-toolchain', 'cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}gdb* ${D}${bindir}', '', d)}
	${@oe.utils.conditional('PREFERRED_PROVIDER_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH}', 'external-arm-sdk-toolchain', 'cp -a ${TOOLCHAIN_PATH}/share/gdb/* ${D}${datadir}/gdb/', '', d)}
	${@oe.utils.conditional('PREFERRED_PROVIDER_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH}', 'external-arm-sdk-toolchain', 'cp -a ${TOOLCHAIN_PATH}/share/info/* ${D}${datadir}/info/', '', d)}
	${@oe.utils.conditional('PREFERRED_PROVIDER_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH}', 'external-arm-sdk-toolchain', 'cp -a ${TOOLCHAIN_PATH}/share/man/man1/${TARGET_PREFIX}* ${D}${datadir}/man/man1/', '', d)}

	cp -a ${TOOLCHAIN_PATH}/${EAT_TARGET_SYS}/bin/ld* ${D}${prefix}/${EAT_TARGET_SYS}/bin
	cp -a ${TOOLCHAIN_PATH}/${EAT_TARGET_SYS}/bin/objcopy ${D}${prefix}/${EAT_TARGET_SYS}/bin
	cp -a ${TOOLCHAIN_PATH}/${EAT_TARGET_SYS}/bin/strip ${D}${prefix}/${EAT_TARGET_SYS}/bin
	cp -a ${TOOLCHAIN_PATH}/${EAT_TARGET_SYS}/bin/nm ${D}${prefix}/${EAT_TARGET_SYS}/bin
	cp -a ${TOOLCHAIN_PATH}/${EAT_TARGET_SYS}/bin/ranlib ${D}${prefix}/${EAT_TARGET_SYS}/bin
	cp -a ${TOOLCHAIN_PATH}/${EAT_TARGET_SYS}/bin/as ${D}${prefix}/${EAT_TARGET_SYS}/bin
	cp -a ${TOOLCHAIN_PATH}/${EAT_TARGET_SYS}/bin/ar ${D}${prefix}/${EAT_TARGET_SYS}/bin
	cp -a ${TOOLCHAIN_PATH}/${EAT_TARGET_SYS}/bin/objdump ${D}${prefix}/${EAT_TARGET_SYS}/bin
	cp -a ${TOOLCHAIN_PATH}/${EAT_TARGET_SYS}/lib/ldscripts/* ${D}${prefix}/${EAT_TARGET_SYS}/lib/ldscripts
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}ld* ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}addr2line ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}objcopy ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}readelf ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}strip ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}nm ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}ranlib ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}gprof ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}as ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}c++filt ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}ar ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}strings ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}objdump ${D}${bindir}
	cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}size ${D}${bindir}
}
