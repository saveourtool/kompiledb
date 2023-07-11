package com.saveourtool.kompiledb

plugins {
    id("org.ajoberstar.reckon")
}

reckon {
    /*
     * See https://github.com/ajoberstar/reckon/blob/0.13.2/docs/index.md
     * for more details.
     */
    scopeFromProp()
    snapshotFromProp()
}
