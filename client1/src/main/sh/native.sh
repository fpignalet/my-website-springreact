#!/usr/bin/env bash

native_setup() {
  SAVE_DIR=${PWD}

  if [[ $PWD == *"/src/main/sh"* ]]; then
    echo FROM CONSOLE: ${SAVE_DIR}
    PATH_CSRC=../c
    PATH_TARGET=../../../target

  else
    echo FROM MAVEN: ${SAVE_DIR}
    PATH_CSRC=src/main/c
    PATH_TARGET=target

  fi

  PATH_LIBS=${PATH_TARGET}/libs
  if [ ! -d ${PATH_TARGET} ]; then
    mkdir ${PATH_TARGET}
    mkdir ${PATH_LIBS}
  fi

  echo setup OK
}

native_make() {
  cd ${PATH_CSRC}
  make
  make clean
  cd ${SAVE_DIR}
}

native_setup
native_make

echo JNI / c compilation finished !
