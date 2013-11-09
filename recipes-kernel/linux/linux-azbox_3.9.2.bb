DESCRIPTION = "Linux kernel for ${MACHINE}"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/linux-${KV}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
MACHINE_KERNEL_PR_append = ".7"

KV = "3.9.2"
SRCDATE = "16092013"
SRCDATE_azboxme = "14092013"
SRCDATE_azboxminime = "14092013"

DEPENDS = "genromfs-native gcc"
DEPENDS_azboxhd = "genromfs-native azbox-hd-buildimage gcc"
DEPENDS_azboxminime = "genromfs-native azbox-minime-packer gcc"

PKG_kernel-base = "kernel-base"
PKG_kernel-image = "kernel-image"
RPROVIDES_kernel-base = "kernel-${KERNEL_VERSION}"
RPROVIDES_kernel-image = "kernel-image-${KERNEL_VERSION}"

SRC_URI += "${KERNELORG_MIRROR}/linux/kernel/v3.x/linux-${KV}.tar.bz2;name=azbox-kernel \
    file://defconfig \
    file://genzbf.c \
    file://sigblock.h \
    file://zboot.h \
    file://emhwlib_registers_tango2.h \
    file://kernel-3.9.2.patch \
    file://add-dmx-source-timecode.patch \
    file://af9015-output-full-range-SNR.patch \
    file://af9033-output-full-range-SNR.patch \
    file://as102-adjust-signal-strength-report.patch \
    file://as102-scale-MER-to-full-range.patch \
    file://cinergy_s2_usb_r2.patch \
    file://cxd2820r-output-full-range-SNR.patch \
    file://dvb-usb-dib0700-disable-sleep.patch \
    file://dvb_usb_disable_rc_polling.patch \
    file://it913x-switch-off-PID-filter-by-default.patch \
    file://tda18271-advertise-supported-delsys.patch \
    file://fix-dvb-siano-sms-order.patch \
    file://mxl5007t-add-no_probe-and-no_reset-parameters.patch \
    file://nfs-max-rwsize-8k.patch \
    file://0001-rt2800usb-add-support-for-rt55xx.patch \
    file://0001-Revert-MIPS-Fix-potencial-corruption.patch \
    "

SRC_URI_append_azboxhd = "http://azbox-enigma2-project.googlecode.com/files/initramfs-${MACHINE}-oe-core-${KV}-${SRCDATE}.tar.bz2;name=azbox-initrd-${MACHINE}"

SRC_URI_append_azboxme = "http://azbox-enigma2-project.googlecode.com/files/initramfs-${MACHINE}-oe-core-${KV}-${SRCDATE}.tar.bz2;name=azbox-initrd-${MACHINE}"

SRC_URI_append_azboxminime = "http://azbox-enigma2-project.googlecode.com/files/initramfs-${MACHINE}-oe-core-${KV}-${SRCDATE}.tar.bz2;name=azbox-initrd-${MACHINE}"

SRC_URI[azbox-kernel.md5sum] = "661100fdf8a633f53991684b555373ba"
SRC_URI[azbox-kernel.sha256sum] = "dfcaa8bf10f87ad04fc46994c3b4646eae914a9eb89e76317fdbbd29f54f1076"
SRC_URI[azbox-initrd-azboxhd.md5sum] = "7effc9bc7eb0ed2e9232dedf6e0c74cc"
SRC_URI[azbox-initrd-azboxhd.sha256sum] = "ff7c86cfc89ffedeaea15cd15ec9839ee97d810ac847527bccc7e1f2ab7ee833"
SRC_URI[azbox-initrd-azboxme.md5sum] = "59f73c9b9fe95183bd39e2a4010a2ac7"
SRC_URI[azbox-initrd-azboxme.sha256sum] = "b98be68bf2d607e57e1cbc48a4eb78c5759d24e0cf0c22e127263788e83665fd"
SRC_URI[azbox-initrd-azboxminime.md5sum] = "3b7508985058ac0a5d9d310f669cc5bc"
SRC_URI[azbox-initrd-azboxminime.sha256sum] = "b7979e03bd53f6c975079761c3399d5dd80e9db5addeae27726f09f87a86be72"

S = "${WORKDIR}/linux-${KV}"

inherit kernel

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "zbimage-linux-xload"
KERNEL_IMAGETYPE = "zbimage-linux-xload"
KERNEL_IMAGEDEST = "/tmp"


FILES_kernel-image = "/boot/zbimage-linux-xload"

CFLAGS_prepend = "-I${WORKDIR} "

do_configure_prepend() {
    oe_machinstall -m 0644 ${WORKDIR}/defconfig ${S}/.config
    oe_runmake oldconfig
}

kernel_do_compile() {
    gcc ${CFLAGS} ${WORKDIR}/genzbf.c -o ${WORKDIR}/genzbf
    install -m 0755 ${WORKDIR}/genzbf ${S}/arch/mips/boot/
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
    oe_runmake ${KERNEL_IMAGETYPE} CC="${KERNEL_CC}" LD="${KERNEL_LD}" AR="${AR}" OBJDUMP="${OBJDUMP}" NM="${NM}"
    oe_runmake modules CC="${KERNEL_CC}" LD="${KERNEL_LD}" AR="${AR}" OBJDUMP="${OBJDUMP}"
}

do_install_append () {
    install -d ${D}/boot
    install -m 0644 ${S}/arch/mips/boot/zbimage-linux-xload ${D}/boot/zbimage-linux-xload

}
