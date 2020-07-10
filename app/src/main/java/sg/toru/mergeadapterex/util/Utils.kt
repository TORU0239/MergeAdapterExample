package sg.toru.mergeadapterex.util

enum class ViewSpanCount {
    HEADER {
        override fun spanCount(): Int = 2
    },

    NOTICE {
        override fun spanCount(): Int = 2
    },

    MAIN {
        override fun spanCount(): Int = 1
    },

    FOOTER {
        override fun spanCount(): Int = 2
    };

    abstract fun spanCount():Int
}

const val HEADER = 0
const val NOTICE = 1
const val MAIN = 2
const val FOOTER = 3

