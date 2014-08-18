# Base this image on generic IVI image
require recipes-yocto-ivi/images/ivi-image.inc

PREFERRED_VERSION_weston = "1.4.%"

PV = "0.1+snapshot-${DATE}"


IMAGE_INSTALL_append = " \
    packagegroup-gdp-qt5 \
    packagegroup-gdp-gps \
    boost \
    automotive-message-broker \
    persistence-common-object \
    persistence-administrator \
    "
