package com.tsitokhtsev.lmsmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tsitokhtsev.lmsmobile.model.SubjectsResponse

class SubjectListAdapter(private val subjectsResponse: SubjectsResponse) :
    RecyclerView.Adapter<SubjectListAdapter.ViewHolder>() {

    private var expandedCardPosition: Int = -1

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subjectCard: CardView
        val expandArrow: ImageView
        val subjectName: TextView
        val subjectInfo: TextView
        val subjectExpandable: ConstraintLayout
        val subjectExpandableInfo: TextView

        init {
            subjectCard = view.findViewById(R.id.subjectCard)
            expandArrow = view.findViewById(R.id.expandArrow)
            subjectName = view.findViewById(R.id.subjectName)
            subjectInfo = view.findViewById(R.id.subjectInfo)
            subjectExpandable = view.findViewById(R.id.subjectExpandable)
            subjectExpandableInfo = view.findViewById(R.id.subjectExpandableInfo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val subject = layoutInflater.inflate(R.layout.subject, parent, false)
        val holder = ViewHolder(subject)

        holder.subjectCard.setOnClickListener {
            notifyItemChanged(expandedCardPosition)

            expandedCardPosition =
                if (expandedCardPosition == holder.adapterPosition) -1
                else holder.adapterPosition

            notifyItemChanged(expandedCardPosition)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSubject = subjectsResponse.data[subjectsResponse.totalCount - position - 1]

        if (position == expandedCardPosition) {
            holder.subjectExpandable.visibility = View.VISIBLE
            holder.expandArrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
        } else {
            holder.subjectExpandable.visibility = View.GONE
            holder.expandArrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
        }

        holder.subjectName.text = currentSubject.name
        holder.subjectInfo.text = currentSubject.gradeOverall.toString()
            .plus(" ქულა · სემესტრი ").plus(currentSubject.semester)
            .plus(" · ").plus(currentSubject.credit).plus(" ECTS")
        holder.subjectExpandableInfo.text = currentSubject.grades
            .substring(0, currentSubject.grades.lastIndexOf(". "))
            .replace(". ", "\n")
    }

    override fun getItemCount(): Int {
        return subjectsResponse.totalCount
    }
}