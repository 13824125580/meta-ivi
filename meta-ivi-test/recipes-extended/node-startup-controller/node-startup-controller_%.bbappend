#
# for test
#
FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append += " \
    file://helper.zip \
    "

do_install_append() {
   install -d ${D}/opt/tests/${PN}
   install -m 0755 ${WORKDIR}/helper/*.sh ${D}/opt/tests/${PN}
   install -m 0644 ${WORKDIR}/helper/*.service ${D}/${systemd_unitdir}/system
}

FILES_${PN}-nsm-dummy += " \
   /opt/tests/${PN}/* \
   ${systemd_unitdir}/system/example.service \
   ${systemd_unitdir}/system/hello.service \
   "
