package com.ht.exceciseinternal.ui.exercise

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ht.exceciseinternal.base.BaseVM
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.beans.Exercise
import com.ht.exceciseinternal.beans.RawExercise
import com.ht.exceciseinternal.database.ExerciseDataBase
import com.ht.exceciseinternal.utility.SingleLiveEvent
import com.ht.exceciseinternal.utility.UIUtils
import com.ht.exceciseinternal.utility.isValid
import com.ht.exceciseinternal.widgets.BaseWC
import com.ht.exceciseinternal.widgets.add_excecise.AddExerciseWC
import com.ht.exceciseinternal.widgets.circuit.CircuitWC
import com.ht.exceciseinternal.widgets.exercise.ExerciseWC
import com.ht.exceciseinternal.widgets.pick_exercise.PickExerciseWC
import kotlinx.coroutines.launch

class ExerciseVM(app: Application) : BaseVM(app) {
    val circuitListLiveData = MediatorLiveData<MutableList<BaseWC>>()
    val exerciseWCListLiveData = MutableLiveData<MutableList<BaseWC>>()
    val pickExerciseWCListLiveData = MutableLiveData<MutableList<BaseWC>>()

    val openCircuitScreenListLiveEvent = SingleLiveEvent<Nothing>() /** open circuits */
    val openCircuitExerciseScreenLiveEvent = SingleLiveEvent<Circuit>() /** open circuit's exercise */
    val openCircuitTimerScreenLiveEvent = SingleLiveEvent<Circuit>() /** open circuit's timer */
    val openExercisePickerScreenLiveEvent = SingleLiveEvent<Nothing>() /** open circuit's timer */

    val closeCircuitExerciseScreenLiveEvent = SingleLiveEvent<Nothing>() /** close circuit's exercise */
    val closeExercisePickerScreenLiveEvent = SingleLiveEvent<Nothing>() /** close exercise picker */
    val circuitNameLiveEvent = SingleLiveEvent<String>() /** circuit name */
    val saveIconEnableLiveEvent = SingleLiveEvent<Boolean>() /** save icon enable */

    val startCircuitLiveEvent = SingleLiveEvent<Circuit>()


    private var selectedCircuit: Circuit? = null
    private var clickedExercise: Exercise? = null

    private val interactor: ExerciseInteractor by lazy { ExerciseInteractor() }
    private val repository: ExerciseRepository by lazy { ExerciseRepository() }

    init {
        databaseObserver()
    }

    override fun <T> notify(actionType: String, actionData: T?) {
        when (actionType) {
            CircuitWC.ACTION_CIRCUIT_CLICK -> handleCircuitClick(actionData as? CircuitWC)

            CircuitWC.ACTION_CIRCUIT_LONG_CLICK -> handleCircuitLongClick(actionData as? CircuitWC)

            ExerciseWC.ACTION_EXERCISE_PICK -> handleExercisePick(actionData as? ExerciseWC)

            ExerciseWC.ACTION_EXERCISE_UPDATE -> handleExerciseUpdates(actionData as? ExerciseWC)

            ExerciseWC.ACTION_EXERCISE_DELETE -> handleExerciseDelete(actionData as? ExerciseWC)

            AddExerciseWC.ACTION_ADD_EXERCISE_CLICK -> handleAddExerciseClick()

            PickExerciseWC.ACTION_RAW_EXERCISE_CLICK -> handlePickExerciseClick(actionData as? PickExerciseWC)

            else -> super.notify(actionType, actionData)
        }
    }

    private fun databaseObserver() {
        val liveData = ExerciseDataBase.getInstance(getApplication()).exerciseDao().getCircuits()
        circuitListLiveData.addSource(liveData) {
            val circuitWCList = interactor.getCircuitConfigList(it, this@ExerciseVM)
            circuitListLiveData.postValue(circuitWCList)
        }
    }

    fun openCircuitScreen() {
        openCircuitScreenListLiveEvent.postValue(null)
    }

    private fun handleCircuitClick(circuitWC: CircuitWC?) {
        val circuit = circuitWC?.circuit
        if (circuit != null) {
            openCircuitTimerScreenLiveEvent.postValue(circuit)
        }
    }

    private fun handleCircuitLongClick(circuitWC: CircuitWC?) {
        val circuit = circuitWC?.circuit
        if (circuit != null) {
            openCircuitExerciseScreenLiveEvent.postValue(circuit)
        }
    }

    fun onFabClick() {
        openCircuitExerciseScreenLiveEvent.postValue(null)
    }

    fun resumeCircuitExercise(circuit: Circuit?) {
        selectedCircuit = circuit

        if (selectedCircuit == null) {
            selectedCircuit = Circuit()
        }

        if (selectedCircuit?.exerciseList == null) {
            selectedCircuit?.exerciseList = mutableListOf()
        }
        if (selectedCircuit?.exerciseList?.isEmpty() == true) {
            selectedCircuit?.exerciseList?.add(Exercise())
        }

        updateCircuitName()

        validateSaveIconEnability()

        getExercises()
    }

    private fun updateCircuitName() {
        val circuitName = selectedCircuit?.name
        circuitNameLiveEvent.postValue(circuitName)
    }

    private fun validateSaveIconEnability() {
        val enableSaveIcon = selectedCircuit?.name?.trim().isNullOrBlank().not() && selectedCircuit?.isValid() == true
        saveIconEnableLiveEvent.postValue(enableSaveIcon)
    }

    private fun getExercises() {
        viewModelScope.launch(coroutineContext) {
            val list = interactor.getExerciseConfigList(selectedCircuit,this@ExerciseVM)
            exerciseWCListLiveData.postValue(list)
        }
    }

    private fun handleExerciseUpdates(exerciseWC: ExerciseWC?) {
        viewModelScope.launch(coroutineContext) {
            interactor.updateExercise(selectedCircuit, exerciseWC?.exercise)
            validateSaveIconEnability()
        }
    }

    private fun handleExerciseDelete(exerciseWC: ExerciseWC?) {
        viewModelScope.launch(coroutineContext) {
            interactor.deleteExercise(selectedCircuit, exerciseWC?.exercise)

            validateSaveIconEnability()

            val list = interactor.getExerciseConfigList(selectedCircuit,this@ExerciseVM)
            exerciseWCListLiveData.postValue(list)
        }
    }

    private fun handleAddExerciseClick() {
        viewModelScope.launch(coroutineContext) {
            interactor.appendExercise(selectedCircuit)

            validateSaveIconEnability()

            val list = interactor.getExerciseConfigList(selectedCircuit,this@ExerciseVM)
            exerciseWCListLiveData.postValue(list)
        }
    }

    fun onCircuitNameChange(text: String?) {
        selectedCircuit?.name = text

        validateSaveIconEnability()
    }

    fun handleSaveCircuitClick() {
        viewModelScope.launch(coroutineContext) {
            if (selectedCircuit == null) {
                selectedCircuit = Circuit()
            }

            ExerciseDataBase.getInstance(getApplication()).exerciseDao().insert(selectedCircuit!!)

            closeCircuitExerciseScreenLiveEvent.postValue(null)
        }
    }

    private fun handleExercisePick(exerciseWC: ExerciseWC?) {
        clickedExercise = exerciseWC?.exercise

        openExercisePickerScreenLiveEvent.postValue(null)
    }

    private fun handlePickExerciseClick(pickExerciseWC: PickExerciseWC?) {
        viewModelScope.launch(coroutineContext) {
            interactor.updateExercise(selectedCircuit, clickedExercise, pickExerciseWC?.rawExercise)

            validateSaveIconEnability()

            val list = interactor.getExerciseConfigList(selectedCircuit,this@ExerciseVM)
            exerciseWCListLiveData.postValue(list)

            closeExercisePickerScreenLiveEvent.postValue(null)
        }
    }

    fun resumeExercisePicker() {
        viewModelScope.launch(coroutineContext) {
            val rawExerciseList = UIUtils.getJsonDataFromAsset<List<RawExercise>>(getApplication())
            val pickExerciseConfigList = interactor.getPickExerciseConfigList(rawExerciseList, this@ExerciseVM)
            pickExerciseWCListLiveData.postValue(pickExerciseConfigList)
        }
    }

    fun resumeCircuitTimer(circuit: Circuit?) {
        startCircuitLiveEvent.postValue(circuit)
    }
}