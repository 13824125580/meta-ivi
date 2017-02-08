SUMMARY = "GENIVI Persistence Client Library"
DESCRIPTION = "The Persistence Management is responsible to handle \
    persistent data, including all data read and modified often during \
    a lifetime of an infotainment system."
HOMEPAGE = "https://at.projects.genivi.org/wiki/display/PROJ/Persistence+Client+Library"
BUGTRACKER = "https://at.projects.genivi.org/jira/browse/PCL"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6161c6840f21a000e9b52af81d2ca823"

DEPENDS = "dlt-daemon dbus libcheck persistence-common-object"

SRCREV = "b433f9686017ac0e9009957034100759b7f0aa6d"
SRC_URI = " \
    git://github.com/GENIVI/${BPN}.git;protocol=https \
    file://0001-load-correct-version-of-libpers_common.patch \
    file://0001-fix-exec-path.patch \
    "
S = "${WORKDIR}/git"

PR = "r3"

inherit pkgconfig autotools-brokensep

EXTRA_OECONF_append = "--enable-tools --enable-pasinterface "

PARALLEL_MAKE = ""

# .so files are the actual libraries
FILES_SOLIBSDEV = ""
#SOLIBS = "${SOLIBSDEV}"

FILES_${PN} += " \
    ${libdir}/lib*custom.so \
    ${libdir}/*.so.* \
    ${sysconfdir} \
    "
FILES_${PN}-dev += " \
    ${libdir}/libpersistence_client_library.so \
    "

do_install_append() {
    install -d ${D}${sysconfdir}/dbus-1/system.d
    install -m 0644 ${S}/config/org.genivi.persistence.admin.conf ${D}${sysconfdir}/dbus-1/system.d
}

RDEPENDS_${PN} = "node-state-manager"
