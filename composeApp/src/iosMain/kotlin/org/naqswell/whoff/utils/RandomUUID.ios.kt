package org.naqswell.whoff.utils

import platform.Foundation.NSUUID

actual fun randomUUID(): String = NSUUID().UUIDString()