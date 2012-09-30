DESCRIPTION = "eCryptfs: A stacked cryptographic filesystem for Linux"
SECTION = "base"
LICENSE = "GPLv2"
DEPENDS = "keyutils libgcrypt libpam openssl"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = "https://launchpad.net/ecryptfs/trunk/100/+download/ecryptfs-utils_100.orig.tar.gz \
           file://ecryptfs-fix-disable-nss.patch \
           file://ecryptfs.service \
          "
SRC_URI[md5sum] = "efaa5eabbd368268874536036397f73b"
SRC_URI[sha256sum] = "a31cb155483d44a59f1506ab53990ac36f81630e0cb3fd2f4dcba35fab614689"


inherit autotools

EXTRA_OECONF += "--disable-nss --disable-pywrap --enable-openssl --prefix=/ --datarootdir=/usr/share"
EXTRA_OEMAKE += "'CFLAGS+= -lgcrypt '"

FILES_${PN} += " \
               ${libdir}/ecryptfs/* \
               ${libdir}/security/pam_ecryptfs.so \
               "

FILES_${PN}-doc += " \
                   /share/locale/* \
                   "

FILES_${PN}-dbg += "${libdir}/ecryptfs/.debug \
                    ${libdir}/security/.debug \
                   "
PACKAGES =+ "${PN}-systemd"

FILES_${PN}-systemd = "${libdir}/systemd/system/"

do_install_append() {
        mkdir -p ${D}/${libdir}/systemd/system
        install -m 644 ${WORKDIR}/ecryptfs.service ${D}/${libdir}/systemd/system
}

# Skip useless warning as we have /usr and / on the same partition
WARN_QA = "ldflags useless-rpaths rpaths staticdev"
