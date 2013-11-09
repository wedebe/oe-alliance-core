DESCRIPTION = "Base packages require for image."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PV = "1.0"
PR = "r4"

inherit task

RDEPENDS = "\
    oe-alliance-base \
    minicattv-enigma2 \
    minicattv-bootlogo \
    mixos-feed-config \	
    libcrypto-compat \
    ntfs-3g \
    hddtemp \
    task-base-smbfs-client \
    task-base-smbfs \
    task-base-nfs \
    busybox-cron \
    "
