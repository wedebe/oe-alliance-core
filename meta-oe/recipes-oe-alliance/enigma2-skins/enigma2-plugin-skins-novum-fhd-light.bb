SUMMARY = "NOVUM_FHD_Light by Nashu"
MAINTAINER = "Nashu"

require conf/license/license-gplv2.inc

inherit gitpkgv allarch

SRCREV = "${AUTOREV}"
PV = "15.6+git${SRCPV}"
PKGV = "15.6+git${GITPKGV}"
PR = "r1"

RDEPENDS_${PN} = "enigma2-plugin-skincomponents-novum"

SRC_URI = "git://github.com/oe-alliance/oe-alliance-skins.git;protocol=git"

FILES_${PN} = "/usr/share/enigma2/NOVUM_FHD_Light"


S = "${WORKDIR}/git"

do_compile_append() {
   python -O -m compileall ${S}
}

do_install() {
   install -d ${D}/usr/share/enigma2
   cp -rp ${S}/Nashu/NOVUM_FHD_Light ${D}/usr/share/enigma2/
   chmod -R a+rX ${D}/usr/share/enigma2/
}

do_package_qa[noexec] = "1"