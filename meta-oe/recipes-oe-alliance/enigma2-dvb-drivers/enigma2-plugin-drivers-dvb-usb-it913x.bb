SUMMARY = "USB DVB driver for it913x chipsets"
PACKAGE_ARCH = "all"

require conf/license/license-gplv2.inc

DVBPROVIDER ?= "kernel"

RRECOMMENDS_${PN} = " \
    ${DVBPROVIDER}-module-dvb-usb-it913x \
    ${DVBPROVIDER}-module-it913x-fe \
    ${DVBPROVIDER}-module-af9033 \
    ${DVBPROVIDER}-module-dvb-usb-af9035 \
    firmware-dvb-usb-it913x \
    firmware-dvb-usb-af9035-02 \
    firmware-dvb-usb-af9035-01 \
    "

PV = "1.0"
PR = "r3"

ALLOW_EMPTY_${PN} = "1"
