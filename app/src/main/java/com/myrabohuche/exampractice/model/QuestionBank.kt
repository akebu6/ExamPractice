package com.myrabohuche.exampractice.model

class QuestionBank(val mQuestionList: List<Question>) {
    private var mNextQuestionIndex: Int = 0

    val question: Question
        get() {
            if (mNextQuestionIndex == mQuestionList.size) {
                mNextQuestionIndex = 0
            }
            return mQuestionList[mNextQuestionIndex++]
        }

    init {
        mQuestionList
        mNextQuestionIndex = 0
    }
}