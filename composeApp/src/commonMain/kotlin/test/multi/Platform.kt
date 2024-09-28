package test.multi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform