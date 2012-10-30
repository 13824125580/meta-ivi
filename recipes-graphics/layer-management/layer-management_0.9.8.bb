DESCRIPTION = "LayerManager"
HOMEPAGE = "https://www.genivi.org/"
SECTION = "environment/base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=249d3578d6bba1bb946148d367a28080"

DEPENDS = "virtual/libgl dbus libxcomposite"

PR = "r0"

SRC_URI = " \
	git://git.projects.genivi.org/layer_management.git;protocol=git;tag=ivi-layer-management_version_0_9_8 \
	file://point-to-internal-CMakeVersions.patch \
	file://include-unistd-for-usleep-in-OpenGLES2App.patch \
	file://include-unistd-for-alarm-in-example.patch \
	file://layermanager.service \
	"

# Needed this for imx6 boards to use precompiled EGL libraries
python () {
    if ((d.getVar("MACHINE", True) or "").find("imx6") != -1):
	flags = d.getVar("OECMAKE_CXX_FLAGS", True)
	flags += " -DLINUX"
	d.setVar('OECMAKE_CXX_FLAGS', flags)
}

S = "${WORKDIR}/git"

inherit gettext cmake systemd

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "layermanager.service"
SYSTEMD_AUTO_ENABLE = "disable"

FILES_${PN} += " \
	${libdir}/lib* \
	${libdir}/layermanager/lib* \
	${libdir}/layermanager/communicator/lib* \
	${libdir}/layermanager/ipcmodules/lib* \
	${libdir}/layermanager/renderer/lib* \
	${libdir}/layermanager/renderer/renderer* \
	"

FILES_${PN}-dev = " \
	${includedir}/* \
	"
FILES_${PN}-staticdev += " \
	${libdir}/layermanager/static/lib* \
	"
FILES_${PN}-dbg += " \
	${libdir}/layermanager/.debug/ \
	${libdir}/layermanager/communicator/.debug/ \
	${libdir}/layermanager/ipcmodules/.debug/ \
	${libdir}/layermanager/renderer/.debug/ \
	"
