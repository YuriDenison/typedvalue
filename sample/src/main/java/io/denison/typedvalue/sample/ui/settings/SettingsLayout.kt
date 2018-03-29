package io.denison.typedvalue.sample.ui.settings

import android.content.Context
import android.util.AttributeSet
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import com.jakewharton.rxrelay2.PublishRelay
import io.denison.typedvalue.sample.R
import io.denison.typedvalue.sample.SampleApplication
import io.denison.typedvalue.sample.mvp.view.MvpConstraintLayout
import io.denison.typedvalue.sample.ui.settings.SettingsView.ViewAction
import io.denison.typedvalue.sample.ui.settings.SettingsView.ViewModel
import io.denison.typedvalue.sample.ui.settings.SettingsView.ViewModel.Content
import io.reactivex.Observable
import kotlinx.android.synthetic.main.ac_main.view.imageTypeSetting
import kotlinx.android.synthetic.main.ac_main.view.imageTypeSettingText
import kotlinx.android.synthetic.main.ac_main.view.scaleSettingValue

class SettingsLayout(context: Context, attributeSet: AttributeSet) : MvpConstraintLayout<SettingsView, SettingsPresenter>(context, attributeSet), SettingsView {
  private lateinit var component: SettingsComponent
  private lateinit var model: ViewModel.Content
  private val actionsRelay = PublishRelay.create<ViewAction>()

  override fun onAttachedToWindow() {
    injectDependencies()
    super.onAttachedToWindow()
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    imageTypeSetting.setOnClickListener { actionsRelay.accept(ViewAction.AnimationTypeClicked(model.type)) }
    scaleSettingValue.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
      override fun onStartTrackingTouch(seekBar: SeekBar?) {}
      override fun onStopTrackingTouch(seekBar: SeekBar?) {}
      override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser)
          actionsRelay.accept(ViewAction.ScaleChanged(progress))
      }
    })
  }

  override fun bindTo(model: ViewModel) = when (model) {
    is ViewModel.Content -> bindContent(model)
  }

  private fun bindContent(model: Content) {
    this.model = model
    imageTypeSettingText.text = model.type.name
    scaleSettingValue.progress = model.scale
  }

  override fun showWelcomeMessage() {
    Toast.makeText(context, R.string.animation_playground, Toast.LENGTH_LONG).show()
  }

  override fun observeViewActions(): Observable<ViewAction> = actionsRelay

  override fun createPresenter(): SettingsPresenter = component.presenter()

  private fun injectDependencies() {
    if (isInEditMode)
      return

    component = DaggerSettingsComponent.builder()
        .appComponent(SampleApplication.appComponent(context))
        .build()
  }
}