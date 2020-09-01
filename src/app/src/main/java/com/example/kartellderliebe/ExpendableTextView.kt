package com.example.kartellderliebe

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseBooleanArray
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import kotlin.properties.Delegates


open class ExpandableTextView : LinearLayout, View.OnClickListener {
    protected var mTv: TextView? = null
    private lateinit var mToggleView: View
    private var mRelayout = false
    private var mCollapsed = true // Show short version as default.
    private var mCollapsedHeight = 0
    private var mTextHeightWithMaxLines = 0
    private var mMaxCollapsedLines = 0
    private var mMarginBetweenTxtAndBottom = 0
    private var mExpandIndicatorController: ExpandIndicatorController? = null
    private var mAnimationDuration = 0
    private var mAnimAlphaStart = 0f
    private var mAnimating = false

    private var mExpandableTextId by Delegates.notNull<Int>()
    private var mExpandCollapseToggleId by Delegates.notNull<Int>()
    private var attrs: AttributeSet? = null

    private var mExpandToggleOnTextClick = false

    /* Listener for callback */
    private var mListener: OnExpandStateChangeListener? = null

    /* For saving collapsed status when used in ListView */
    private var mCollapsedStatus: SparseBooleanArray? = null
    private var mPosition = 0

    @JvmOverloads
    constructor(
        context: Context?,
        attrs: AttributeSet? = null
    ) : super(context, attrs) {
        //init(attrs)
        this.attrs = attrs
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int,
        attrs1: AttributeSet?
    ) : super(context, attrs, defStyle) {
        //init(attrs)
    }

    override fun setOrientation(orientation: Int) {
        require(HORIZONTAL != orientation) { "ExpandableTextView only supports Vertical Orientation." }
        super.setOrientation(orientation)
    }

    override fun onClick(view: View) {
        if (mToggleView.visibility != View.VISIBLE) {
            return
        }
        mCollapsed = !mCollapsed
        mExpandIndicatorController!!.changeState(mCollapsed)
        if (mCollapsedStatus != null) {
            mCollapsedStatus!!.put(mPosition, mCollapsed)
        }

        // mark that the animation is in progress
        mAnimating = true
        val animation: Animation = if (mCollapsed) {
            ExpandCollapseAnimation(this, height, mCollapsedHeight)
        } else {
            ExpandCollapseAnimation(
                this, height, height +
                        mTextHeightWithMaxLines - mTv!!.height
            )
        }
        animation.fillAfter = true
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                applyAlphaAnimation(mTv, mAnimAlphaStart)
            }

            override fun onAnimationEnd(animation: Animation) {
                // clear animation here to avoid repeated applyTransformation() calls
                clearAnimation()
                // clear the animation flag
                mAnimating = false

                // notify the listener
                if (mListener != null) {
                    mListener!!.onExpandStateChanged(mTv, !mCollapsed)
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        clearAnimation()
        startAnimation(animation)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        // while an animation is in progress, intercept all the touch events to children to
        // prevent extra clicks during the animation
        return mAnimating
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // If no change, measure and return
        if (!mRelayout || visibility == View.GONE) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }
        mRelayout = false

        // Setup with optimistic case
        // i.e. Everything fits. No button needed
        mToggleView.visibility = View.GONE
        mTv!!.maxLines = Int.MAX_VALUE

        // Measure
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // If the text fits in collapsed mode, we are done.
        if (mTv!!.lineCount <= mMaxCollapsedLines) {
            return
        }

        // Saves the text height w/ max lines
        mTextHeightWithMaxLines = getRealTextViewHeight(mTv!!)

        // Doesn't fit in collapsed mode. Collapse text view as needed. Show
        // button.
        if (mCollapsed) {
            mTv!!.maxLines = mMaxCollapsedLines
        }
        mToggleView.visibility = View.VISIBLE

        // Re-measure with new setup
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (mCollapsed) {
            // Gets the margin between the TextView's bottom and the ViewGroup's bottom
            mTv!!.post { mMarginBetweenTxtAndBottom = height - mTv!!.height }
            // Saves the collapsed height of this ViewGroup
            mCollapsedHeight = measuredHeight
        }
    }

    fun setOnExpandStateChangeListener(@Nullable listener: OnExpandStateChangeListener?) {
        mListener = listener
    }

    fun setText(
        @Nullable text: CharSequence?,
        collapsedStatus: SparseBooleanArray,
        position: Int
    ) {
        mCollapsedStatus = collapsedStatus
        mPosition = position
        val isCollapsed = collapsedStatus[position, true]
        clearAnimation()
        mCollapsed = isCollapsed
        mExpandIndicatorController!!.changeState(mCollapsed)
        this@ExpandableTextView.text = text
    }

    @get:Nullable
    var text: CharSequence?
        get() = if (mTv == null) {
            ""
        } else mTv!!.text
        set(text) {
            mRelayout = true
            mTv!!.text = text
            visibility = if (TextUtils.isEmpty(text)) View.GONE else View.VISIBLE
            clearAnimation()
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            requestLayout()
        }

    public fun setAttr(id1 : Int, id2 : Int){
        mExpandableTextId = id1
        mExpandCollapseToggleId = id2
        init((attrs))
        findViews()
    }

    private fun init(attrs: AttributeSet?) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView)
        mMaxCollapsedLines = typedArray.getInt(
            R.styleable.ExpandableTextView_maxCollapsedLines,
            MAX_COLLAPSED_LINES
        )
        mAnimationDuration = typedArray.getInt(
            R.styleable.ExpandableTextView_animDuration,
            DEFAULT_ANIM_DURATION
        )
        mAnimAlphaStart = typedArray.getFloat(
            R.styleable.ExpandableTextView_animAlphaStart,
            DEFAULT_ANIM_ALPHA_START
        )
        mExpandToggleOnTextClick =
            typedArray.getBoolean(R.styleable.ExpandableTextView_expandToggleOnTextClick, true)
        mExpandIndicatorController =
            setupExpandToggleController(context, typedArray)
        typedArray.recycle()

        // enforces vertical orientation
        orientation = VERTICAL

        // default visibility is gone
        visibility = View.GONE
    }

    private fun findViews() {
        mTv = findViewById<View>(mExpandableTextId) as TextView
        if (mExpandToggleOnTextClick) {
            mTv!!.setOnClickListener(this)
        } else {
            mTv!!.setOnClickListener(null)
        }
        mToggleView = findViewById(mExpandCollapseToggleId)
        mExpandIndicatorController!!.setView(mToggleView)
        mExpandIndicatorController!!.changeState(mCollapsed)
        mToggleView.setOnClickListener(this)
    }

    internal inner class ExpandCollapseAnimation(
        private val mTargetView: View,
        private val mStartHeight: Int,
        private val mEndHeight: Int
    ) :
        Animation() {
        override fun applyTransformation(
            interpolatedTime: Float,
            t: Transformation
        ) {
            val newHeight =
                ((mEndHeight - mStartHeight) * interpolatedTime + mStartHeight).toInt()
            mTv!!.maxHeight = newHeight - mMarginBetweenTxtAndBottom
            if (mAnimAlphaStart.compareTo(1.0f) != 0) {
                applyAlphaAnimation(
                    mTv,
                    mAnimAlphaStart + interpolatedTime * (1.0f - mAnimAlphaStart)
                )
            }
            mTargetView.layoutParams.height = newHeight
            mTargetView.requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }

        init {
            duration = mAnimationDuration.toLong()
        }
    }

    interface OnExpandStateChangeListener {
        /**
         * Called when the expand/collapse animation has been finished
         *
         * @param textView - TextView being expanded/collapsed
         * @param isExpanded - true if the TextView has been expanded
         */
        fun onExpandStateChanged(textView: TextView?, isExpanded: Boolean)
    }

    internal interface ExpandIndicatorController {
        fun changeState(collapsed: Boolean)
        fun setView(toggleView: View?)
    }

    internal class ImageButtonExpandController(
        private val mExpandDrawable: Drawable?,
        private val mCollapseDrawable: Drawable?
    ) :
        ExpandIndicatorController {
        private var mImageButton: ImageButton? = null
        override fun changeState(collapsed: Boolean) {
            mImageButton!!.setImageDrawable(if (collapsed) mExpandDrawable else mCollapseDrawable)
        }

        override fun setView(toggleView: View?) {
            mImageButton = toggleView as ImageButton?
        }

    }

    internal class TextViewExpandController(
        private val mExpandText: String?,
        private val mCollapseText: String?
    ) :
        ExpandIndicatorController {
        private var mTextView: TextView? = null
        override fun changeState(collapsed: Boolean) {
            mTextView!!.text = if (collapsed) mExpandText else mCollapseText!!.subSequence(
                0,
                mCollapseText.length - 5
            )
        }

        override fun setView(toggleView: View?) {
            mTextView = toggleView as TextView?
        }

    }

    companion object {
        private val TAG = ExpandableTextView::class.java.simpleName
        private const val EXPAND_INDICATOR_IMAGE_BUTTON = 0
        private const val EXPAND_INDICATOR_TEXT_VIEW = 1
        private const val DEFAULT_TOGGLE_TYPE =
            EXPAND_INDICATOR_IMAGE_BUTTON

        /* The default number of lines */
        private const val MAX_COLLAPSED_LINES = 5

        /* The default animation duration */
        private const val DEFAULT_ANIM_DURATION = 300

        /* The default alpha value when the animation starts */
        private const val DEFAULT_ANIM_ALPHA_START = 0.7f
        private val isPostHoneycomb: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB

        private val isPostLolipop: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        private fun applyAlphaAnimation(
            view: View?,
            alpha: Float
        ) {
            if (isPostHoneycomb) {
                view!!.alpha = alpha
            } else {
                val alphaAnimation = AlphaAnimation(alpha, alpha)
                // make it instant
                alphaAnimation.duration = 0
                alphaAnimation.fillAfter = true
                view!!.startAnimation(alphaAnimation)
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private fun getDrawable(
            context: Context,
            @DrawableRes resId: Int
        ): Drawable {
            val resources = context.resources
            return if (isPostLolipop) {
                resources.getDrawable(resId, context.theme)
            } else {
                resources.getDrawable(resId)
            }
        }

        private fun getRealTextViewHeight(textView: TextView): Int {
            val textHeight = textView.layout.getLineTop(textView.lineCount)
            val padding =
                textView.compoundPaddingTop + textView.compoundPaddingBottom
            return textHeight + padding
        }

        private fun setupExpandToggleController(
            context: Context,
            typedArray: TypedArray
        ): ExpandIndicatorController {
            val expandToggleType = typedArray.getInt(
                R.styleable.ExpandableTextView_expandToggleType,
                DEFAULT_TOGGLE_TYPE
            )
            val expandIndicatorController: ExpandIndicatorController
            when (expandToggleType) {
                EXPAND_INDICATOR_IMAGE_BUTTON -> {
                    var expandDrawable =
                        typedArray.getDrawable(R.styleable.ExpandableTextView_expandIndicator)
                    var collapseDrawable =
                        typedArray.getDrawable(R.styleable.ExpandableTextView_collapseIndicator)
                    if (expandDrawable == null) {
                        expandDrawable = getDrawable(
                            context,
                            R.drawable.ic_expand_more_black_12dp
                        )
                    }
                    if (collapseDrawable == null) {
                        collapseDrawable = getDrawable(
                            context,
                            R.drawable.ic_expand_less_black_12dp
                        )
                    }
                    expandIndicatorController = ImageButtonExpandController(
                        expandDrawable,
                        collapseDrawable
                    )
                }
                EXPAND_INDICATOR_TEXT_VIEW -> {
                    val expandText =
                        typedArray.getString(R.styleable.ExpandableTextView_expandIndicator)
                    val collapseText =
                        typedArray.getString(R.styleable.ExpandableTextView_collapseIndicator)
                    expandIndicatorController =
                        TextViewExpandController(expandText, collapseText)
                }
                else -> throw IllegalStateException("Must be of enum: ExpandableTextView_expandToggleType, one of EXPAND_INDICATOR_IMAGE_BUTTON or EXPAND_INDICATOR_TEXT_VIEW.")
            }
            return expandIndicatorController
        }
    }
}