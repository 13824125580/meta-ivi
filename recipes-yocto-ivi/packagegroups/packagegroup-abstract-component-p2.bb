DESCRIPTION = "GENIVI P2 Abstract components (AC)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${IVI_COREBASE}/meta-ivi/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
# Avoid hardcoding the full layer path into the checksums
LIC_FILES_CHKSUM[vardepsexclude] += "IVI_COREBASE"
PR = "r0"

inherit packagegroup

PACKAGES = "\
    packagegroup-abstract-component-p2 \
    "

ALLOW_EMPTY_${PN} = "1"


RDEPENDS_${PN} += "\
    node-state-manager \
    ofono \
    persistence-client-library \
    "
