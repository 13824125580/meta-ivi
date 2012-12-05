PRINC := "${@int(PRINC) + 3}"

LIB_DEPS += "virtual/libgl"

FILESEXTRAPATHS := "${THISDIR}/${PN}"

inherit systemd
SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "X.service"

SRC_URI_append = " file://X.service \
                 "
