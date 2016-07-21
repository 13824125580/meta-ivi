FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI_append = " file://remove_add_gmock.patch "

EXTRA_OECMAKE_remove = "-DWITH_TESTS=OFF"
EXTRA_OECMAKE_append = " -DWITH_TEST_CONTROLLER=ON -DWITH_TESTS=ON"

DEPENDS += "gtest gmock"

do_install_append() {
    make plugin-test-install
    mv ${D}/opt/tests/audiomanager ${D}/opt/tests/${PN}
}

PACKAGES += " ${PN}-test"
FILES_${PN}-dbg += "/opt/tests/${PN}/.debug/* "
FILES_${PN}-test = "/opt/tests/${PN}/* "
