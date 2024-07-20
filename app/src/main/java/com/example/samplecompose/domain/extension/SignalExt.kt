package com.example.samplecompose.domain.extension

import com.example.samplecompose.domain.model.Signal


fun <T : Signal> T.update(block: T.() -> Unit) {
    block(this)
    this.emit()
}