#!/bin/sh
ROOT_DIR=`cd ../../;pwd`
ant -f ${ROOT_DIR}/utility/ant/mac.xml jar && mv ${ROOT_DIR}/utility/ant/idumo.jar ${ROOT_DIR}/test/android/libs/idumo.jar
