meta-ivi, the Yocto layer for In-Vehicle Infotainment
=====================================================

This layer's purpose is to add In-Vehicle Infotainment (IVI) support when
used with Poky.  The goal is to make the Yocto Project reference system
Poky GENIVI compliant.

Please see the
[MAINTAINERS](http://git.yoctoproject.org/cgit/cgit.cgi/meta-ivi/tree/MAINTAINERS)
file for information on contacting the maintainers
of this layer, as well as instructions for submitting patches.

The meta-ivi project welcomes contributions. You can contribute code,
submit patches, report bugs, answer questions on our mailing lists and
review and edit our documentation and much more.

Subscribe to the mailing list
    [here](https://lists.genivi.org/mailman/listinfo/genivi-meta-ivi).  
View or Report bugs
    [here](https://bugs.genivi.org/buglist.cgi?product=meta-ivi).  
Read or Edit the wiki
    [here](http://wiki.projects.genivi.org/index.php/meta-ivi).  
Vote or Comment on our plan
    [here](https://trello.com/b/HplBZa2l/meta-ivi-development).  
For information about the Yocto Project, see the
    [Yocto Project website](https://www.yoctoproject.org).  
For information about the Yocto GENIVI Baseline, see the
    [Yocto GENIVI Baseline website](http://projects.genivi.org/GENIVI_Baselines/meta-ivi).

Layer Dependencies
------------------

URI: git://git.yoctoproject.org/poky
> branch:   master
> revision: 8ac8eca2e3bd8c78e2b31ea974930ed0243258a3

URI: git://git.openembedded.org/meta-openembedded
> branch:   master
> revision: 0d01e1b72333f49c29d1a27ad844c4cd9f90341c

Using the above git sha's and the master meta-ivi branch, bitbaking intrepid-image
is known to work (the intrepid-image build should be aligned with GENIVI 7.0).

For creating a specific GENIVI compliant image version, please make sure you
git checkout the related meta-ivi branch and follow the build instructions
located in the README.md file of that branch.  So for example, to build
an image that should be GENIVI 6.0 compliant, checkout the meta-ivi 6.0 branch,
and follow the README.md part of that branch.  As does the GENIVI Alliance
we only support the current and the previous version.  Any version older
than that is not supported any more, and therefore may not build or run.

Supported Machines
------------------

We do smoke test the builds of the three machines that we currently support:

* QEMU (ARMv7) - emulated machine: vexpressa9
* QEMU (IA-32) - emulated machine: qemux86
* QEMU (x86-64) - emulated machine: qemux86-64

Please check on our [wiki](http://wiki.projects.genivi.org/index.php/meta-ivi)
regarding any community supported machines.
For example there Renesas provides a public Board Support Package (BSP)
available for use with meta-ivi.

Build a QEMU image that contains GENIVI components
--------------------------------------------------

You can build a QEMU image that should be GENIVI compliant using the
following steps:

1. Run the following command:
   > $ source poky/oe-init-build-env

2. Add the meta-oe, meta-ivi and meta-ivi-bsp layer path to the
$BUILDDIR/conf/bblayers.conf BBLAYERS variable.

3. Set MACHINE ??= "vexpressa9", MACHINE ??= "qemux86" or MACHINE ??= "qemux86-64"
in $BUILDDIR/conf/local.conf file to build for an emulated ARMv7a, x86 or x86-64
instruction-set maschine respectively.

4. Add INCOMPATIBLE_LICENSE = "GPLv3" in $BUILDDIR/conf/local.conf file.

5. Set DISTRO ?= "poky-ivi-systemd" in $BUILDDIR/conf/local.conf file.

6. Optional: In $BUILDDIR/conf/local.conf file, you may uncomment
BB_NUMBER_THREADS = "4" and PARALLEL_MAKE = "-j 4" if you build on a
quad-core machine.

7. Build intrepid-image including GENIVI 7.0 (Intrepid) components
   > $ bitbake intrepid-image

8. Run the emulator:
   > for qemu vexpressa9:  
   > $ PATH_TO_META_IVI/meta-ivi/scripts/runqemu intrepid-image vexpressa9
   >
   > for qemu x86:  
   > $ PATH_TO_POKY/poky/scripts/runqemu intrepid-image qemux86
   >
   > for qemu x86-64:  
   > $ PATH_TO_POKY/poky/scripts/runqemu intrepid-image qemux86-64

9. To login use these credentials:
   > User - root
   > Password - root

You may point an environment variable named TEMPLATECONF to the meta-ivi/conf
directory for the template local.conf & bblayers.conf files; in which case you
may skip steps 2. to 6.
