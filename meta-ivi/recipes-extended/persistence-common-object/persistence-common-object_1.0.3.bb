SUMARY = "Common persistence object used by client library and administrator"
HOMEPAGE = "http://projects.genivi.org/persistence-client-library"
BUGTRACKER = "http://bugs.genivi.org/enter_bug.cgi?product=Persistence"
SECTION = "base"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = " \
    git://git.projects.genivi.org/persistence/persistence-common-object.git;tag=${PV} \
    file://configure.ac-fix-typo.patch \
    file://B251_typedef_uint64_t.patch \
    "
#link for bug 251 in bugzilla https://bugs.genivi.org/show_bug.cgi?id=251

DEPENDS = "glib-2.0 libitzam dlt-daemon"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

EXTRA_OECONF = "--with-dbuspolicydir=${sysconfdir} --with-database=key-value-store"

FILES_${PN} += "${datadir}"

#
# for test
#
EXTRA_OECONF += "--enable-tests"

do_install_append() {
   install -d ${D}${bindir}
   install -m 0755 ${S}/test/.libs/test_pco_key_value_store ${D}${bindir}
}

PACKAGES += "${PN}-testenv"
RDEPENDS_${PN} += "${PN}-testenv"

FILES_${PN} = " \
    ${libdir}/*.so.* \
    ${sysconfdir} \
    ${datadir}/dbus-1/interfaces/* \
    "

FILES_${PN}-testenv = " \
   ${bindir}/test_pco_key_value_store \
   "
