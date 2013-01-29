SUMMARY = "GENIVI Node Startup Controller"
DESCRIPTION = "The GENIVI Node Startup Controller \
		- manages the Last User Context (LUC) \
		- monitors node startup \
		  and \
		- manages legacy applications within a node \
		"

HOMEPAGE = "https://www.genivi.org/"
SECTION = "base"

LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

# tag 1.0.1 : f109eab4393fcb55ecbb0a21d68436a5057a6b82
SRC_URI = "git://git.projects.genivi.org/lifecycle/node-startup-controller.git;protocol=git;tag=f109eab4393fcb55ecbb0a21d68436a5057a6b82 \
           file://use-systemd-unit-dir.patch"
PR = "r3"

DEPENDS = "glib-2.0 dlt-daemon systemd"

S = "${WORKDIR}/git"

inherit autotools gtk-doc systemd
do_configure_prepend () {
        mkdir -p m4
}

PACKAGES =+ "${PN}-nsm-dummy ${PN}-nsm-dummy-dbg"

SYSTEMD_PACKAGES = "${PN}-systemd ${PN}-nsm-dummy-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "node-startup-controller.service"
SYSTEMD_SERVICE_${PN}-nsm-dummy-systemd = "nsm-dummy.service"
SYSTEMD_AUTO_ENABLE = "disable"

RRECOMMENDS_${PN} += "${PN}-nsm-dummy"

FILES_${PN} += "\
	${libdir}/${PN}-1/${PN} \
	${libdir}/${PN}-1/legacy-app-handler \
	${datadir}/dbus-1/system-services/org.genivi.NodeStartupController1.service \
	"
FILES_${PN}-dbg += "\
	${libdir}/${PN}-1/.debug/*ler \
	"
FILES_${PN}-nsm-dummy = "\
	${libdir}/${PN}-1/nsm-dummy \
	${datadir}/dbus-1/system-services/org.genivi.NodeStateManager.* \
	"
FILES_${PN}-nsm-dummy-dbg = "\
	${libdir}/${PN}-1/.debug/nsm-dummy \
	"
