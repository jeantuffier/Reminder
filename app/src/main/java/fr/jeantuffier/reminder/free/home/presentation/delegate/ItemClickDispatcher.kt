package fr.jeantuffier.reminder.free.home.presentation.delegate

import io.reactivex.subjects.PublishSubject

object ItemClickDispatcher {
    
    var publishSubject: PublishSubject<Int>? = null

}