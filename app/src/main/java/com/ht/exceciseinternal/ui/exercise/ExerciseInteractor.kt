package com.ht.exceciseinternal.ui.exercise

import com.ht.exceciseinternal.base.BaseInteractor
import com.ht.exceciseinternal.base.VMNotifier
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.beans.Exercise
import com.ht.exceciseinternal.beans.RawExercise
import com.ht.exceciseinternal.widgets.BaseWC
import com.ht.exceciseinternal.widgets.add_excecise.AddExerciseWC
import com.ht.exceciseinternal.widgets.circuit.CircuitWC
import com.ht.exceciseinternal.widgets.exercise.ExerciseWC
import com.ht.exceciseinternal.widgets.pick_exercise.PickExerciseWC


class ExerciseInteractor: BaseInteractor() {
    fun getCircuitConfigList(circuitList: List<Circuit>?, vmNotifier: VMNotifier): MutableList<BaseWC> {
        val returnValue = mutableListOf<BaseWC>()

        for (circuit in circuitList ?: mutableListOf()) {
            returnValue.add(CircuitWC(circuit).apply { this.vmNotifier = vmNotifier })
        }

        return returnValue
    }

    fun getExerciseConfigList(circuit: Circuit?, vmNotifier: VMNotifier): MutableList<BaseWC> {
        val returnValue = mutableListOf<BaseWC>()

        for (exercise in circuit?.exerciseList ?: mutableListOf()) {
            returnValue.add(ExerciseWC(exercise).apply { this.vmNotifier = vmNotifier })
        }

        returnValue.add(AddExerciseWC().apply { this.vmNotifier = vmNotifier })

        return returnValue
    }

    fun updateExercise(circuit: Circuit?, updatedExercise: Exercise?) {
        if (circuit != null && updatedExercise != null) {
            for (exercise in circuit.exerciseList ?: mutableListOf()) {
                if (exercise.exerciseId == updatedExercise.exerciseId) {
                    exercise.apply {
                        name = updatedExercise.name
                        exerciseDuration = updatedExercise.exerciseDuration
                        restDuration = updatedExercise.restDuration
                    }
                    break
                }
            }
        }
    }

    fun deleteExercise(circuit: Circuit?, deletedExercise: Exercise?) {
        if (circuit != null && deletedExercise != null) {
            for (exercise in circuit.exerciseList ?: mutableListOf()) {
                if (exercise.exerciseId == deletedExercise.exerciseId) {
                    circuit.exerciseList?.remove(exercise)
                    break
                }
            }
        }
    }

    fun appendExercise(circuit: Circuit?) {
        val exerciseWCList = circuit?.exerciseList ?: return
        exerciseWCList.add(Exercise())
    }

    fun getPickExerciseConfigList(rawExerciseList: List<RawExercise>?, vmNotifier: VMNotifier): MutableList<BaseWC> {
        val returnValue = mutableListOf<BaseWC>()

        for (rawExercise in rawExerciseList ?: mutableListOf()) {
            returnValue.add(PickExerciseWC(rawExercise).apply { this.vmNotifier = vmNotifier })
        }

        return returnValue
    }

    fun updateExercise(circuit: Circuit?, clickedExercise: Exercise?, rawExercise: RawExercise?) {
        if (circuit != null && clickedExercise != null && rawExercise != null) {
            for (exercise in circuit.exerciseList ?: mutableListOf()) {
                if (exercise.exerciseId == clickedExercise.exerciseId) {
                    exercise.apply {
                        name = rawExercise.name
                        imageName = rawExercise.imageName
                        exerciseAudio = rawExercise.audio
                    }
                    break
                }
            }
        }
    }
}