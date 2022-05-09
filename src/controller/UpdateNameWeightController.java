package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import entity.*;
import boundary.*;

public class UpdateNameWeightController implements ActionListener {
    public static enum UpdateNWProblem {NO_ERROR, EMPTY_NAME, DUPLICATE_NAME, BAD_FLOAT};
    private IGraderFrame rootView;
    private IGraderFrame parentView;
    private User user;
    private Course course;
    private RealAssignment parentTemplate;
    private EditAssignmentView editAssignmentInfo;

    public UpdateNameWeightController(IGraderFrame rootView, IGraderFrame parentView,
    		User user, Course course, RealAssignment parent,
    		EditAssignmentView editAssignmentView) {
        this.rootView = rootView;
        this.parentView = parentView;
        this.user = user;
        this.course = course;
        this.parentTemplate = parent;
        this.editAssignmentInfo = editAssignmentView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UpdateNWProblem error = validateInformation();

        if (error == UpdateNWProblem.NO_ERROR) {
            String updatedName = editAssignmentInfo.getUpdatedName();
            Float updatedWeight = editAssignmentInfo.getUpdatedWeight() / 100.0f;

            // first update entry information
            updateAllEntryInformation(updatedName, updatedWeight);

            // after updating entry, update parent information
            this.parentTemplate.setName(updatedName);
            this.parentTemplate.setWeight(updatedWeight);

            // update entry subAssignment names and weights
            updateAllEntrySubAssignmentInformation();

            // update template subassignment information
            // and add/remove sub-assignments as necessary
            updateTemplateSubAssignments();

            // Close window
            returnToParentView();
        }
        else {
            editAssignmentInfo.showError(error);
            rootView.update();
            rootView.display();
        }
    }
    
    private UpdateNWProblem validateInformation() {

        String updatedName = editAssignmentInfo.getUpdatedName();

        // Check empty name
        if (updatedName.isEmpty()) {
            return UpdateNWProblem.EMPTY_NAME;
        }
        // Check if name is duplicate now
        if(nameIsDuplicate(updatedName, course.getTemplate()))
        {
        	return UpdateNWProblem.DUPLICATE_NAME;
        }

        // Checks for bad float (empty or invalid input)
        try {
            Float updatedWeight = editAssignmentInfo.getUpdatedWeight();
            if(updatedWeight > 100 || updatedWeight < 0)
            {
            	return UpdateNWProblem.BAD_FLOAT;
            }
        }
        catch (Exception e) {
            return UpdateNWProblem.BAD_FLOAT;
        }

        return UpdateNWProblem.NO_ERROR;
    }
    
    private boolean nameIsDuplicate(String updatedName, RealAssignment ra)
    {
    	// Check this assignment's name
    	if(ra.getName().equalsIgnoreCase(updatedName) &&
    			ra.getID() != parentTemplate.getID())  // Second case here is for unchanged name
    	{
    		// Have a duplicate
    		return true;
    	}
    	
    	// Check all other sub-assignment names
    	for(int i = 0; i < ra.getNumSubAssignments(); i++)
    	{
    		// Can cast because checked above (numSubAssignments !=0 )
    		RealAssignment sa = (RealAssignment) ra.getSubAssignment(i);
    		if(nameIsDuplicate(updatedName, sa))
    		{
    			return true;
    		}
    	}
    	
    	// If we get here, didn't find any duplicate names
    	return false;
    }

    private void returnToParentView() {
        // Close the window
        rootView.closeWindow();

        // Refresh the parent
        parentView.update();
        parentView.display();
    }

    private void updateAllEntryInformation(String updatedName, float updatedWeight) {
        course.getEntries().stream()
                .map(entry -> entry.getFinalGrade())
                .forEach(realAssignment -> updateRAInformation(realAssignment, updatedName, updatedWeight));
    }

    private void updateRAInformation(RealAssignment ra, String updatedName, float updatedWeight) {
        for (int i=0; i< ra.getNumSubAssignments(); i++) {
        	// Can cast because checked above that numSubAssignments != 0
            RealAssignment subRa = (RealAssignment) ra.getSubAssignment(i);

            if (subRa.equals(parentTemplate)) {
                subRa.setName(updatedName);
                subRa.setWeight(updatedWeight);
            }
            
            // Recursively look to update among any sub-assignments of subRa
            updateRAInformation(subRa, updatedName, updatedWeight);
        }
    }

    private void updateTemplateSubAssignments() {
    	// Determine (potentially new) number of sub-assignments for parent
		ArrayList<String> subAssignmentNames = editAssignmentInfo.getSubAssignmentNames();
		ArrayList<Float> subAssignmentWeights = editAssignmentInfo.getSubAssignmentWeights();
    	
    	/*
		 *  3 cases: 1. need to add new sub-assignment(s)
		 *           2. need to remove sub-assignment(s)
		 *           3. have same number of sub-assignments
		 */
    	if(subAssignmentNames.size() > parentTemplate.getNumSubAssignments())  // Case 1
		{
    		// Just need to update existing sub-assignments...
			for(int i = 0; i < parentTemplate.getNumSubAssignments(); i++)
			{
				// Get sub-assignment
				// Can cast because numSubAssignment > 0, so have at least one
				RealAssignment subAssignment = (RealAssignment) parentTemplate.getSubAssignment(i);
				
				// Update sub-assignment
				subAssignment.setName(subAssignmentNames.get(i));
				subAssignment.setWeight(subAssignmentWeights.get(i));
			}
			
			// ...and add the remaining assignments to template
			int newAssignmentIndex = parentTemplate.getNumSubAssignments();
			for(; newAssignmentIndex < subAssignmentNames.size(); newAssignmentIndex++)
			{
				// Create new RealAssignment
				RealAssignment newAssignment = new RealAssignment(
						subAssignmentNames.get(newAssignmentIndex),
						subAssignmentWeights.get(newAssignmentIndex),
						new Grade(0f),
						parentTemplate.getStudent());
				
				// Add assignment
				course.getTemplate().addSubAssignment(newAssignment, parentTemplate);
				course.addAssignment(newAssignment, parentTemplate);  // This takes care of entries automatically
			}
		}
    	else if(subAssignmentNames.size() < parentTemplate.getNumSubAssignments())  // Case 2
		{
    		// Just need to update the names and weights that we have entries for...
    		for(int i = 0; i < subAssignmentNames.size(); i++)
    		{
    			// Get sub-assignment
    			// Can cast because sub-assignment field size > 0, so have at least one
    			RealAssignment subAssignment = (RealAssignment) parentTemplate.getSubAssignment(i);

    			// Update sub-assignment
    			subAssignment.setName(subAssignmentNames.get(i));
    			subAssignment.setWeight(subAssignmentWeights.get(i));
    		}

    		// ...and remove the remaining assignments from template
    		int removeIndex = subAssignmentNames.size();
    		int removeEndIndex = parentTemplate.getNumSubAssignments();
    		for(; removeIndex < removeEndIndex; removeIndex++)
    		{
    			// Get assignment to remove
    			// Can cast because checked above that deleteIndex < numSubAssignments
    			// and that numSubAssignments > 0
    			RealAssignment assignmentToRemove = (RealAssignment) parentTemplate.getSubAssignment(removeIndex);

    			// Remove assignment
    			course.getTemplate().removeSubAssignment(assignmentToRemove);
    			course.removeAssignment(assignmentToRemove);  // This takes care of entries automatically
    		}
		}
    	else  // Case 3
		{
    		// Just need to update names and weights of sub-assignments
			for(int i = 0; i < parentTemplate.getNumSubAssignments(); i++)
			{
				// Get sub-assignment
				// Can cast because checked that numSubAssignments != 0 above
				RealAssignment subAssignment = (RealAssignment) parentTemplate.getSubAssignment(i);
				
				// Update sub-assignment
				subAssignment.setName(subAssignmentNames.get(i));
				subAssignment.setWeight(subAssignmentWeights.get(i));
			}
		}
    }

    private void updateAllEntrySubAssignmentInformation() {
    	// Get all <parent> in a list
    	ArrayList<RealAssignment> parentAssignmentList = new ArrayList<RealAssignment>();
    	for(Entry e: course.getEntries())
    	{
    		RealAssignment entryAssignment = e.getAssignment(parentTemplate);
    		if(entryAssignment != null)  // If found parent in entry
    		{
    			parentAssignmentList.add(entryAssignment);
    		}
    	}

    	// For each entry's version of <parent>
    	for (RealAssignment entryAssignment : parentAssignmentList)
    	{
    		// Determine (potentially new) number of sub-assignments for parent
    		ArrayList<String> subAssignmentNames = editAssignmentInfo.getSubAssignmentNames();
    		ArrayList<Float> subAssignmentWeights = editAssignmentInfo.getSubAssignmentWeights();
    		
    		/*
    		 *  3 cases: 1. need to add new sub-assignment(s)
    		 *           2. need to remove sub-assignment(s)
    		 *           3. have same number of sub-assignments
    		 *           
    		 *  Will only be updating names and weights here.
    		 *  Add assignments in updateSubAssignments()
    		 */
    		if(subAssignmentNames.size() > entryAssignment.getNumSubAssignments())  // Case 1
    		{
    			// Just need to update existing sub-assignments
    			for(int i = 0; i < entryAssignment.getNumSubAssignments(); i++)
    			{
    				// Get sub-assignment
    				// Can cast because numSubAssignment > 0, so have at least one
    				RealAssignment subAssignment = (RealAssignment) entryAssignment.getSubAssignment(i);
    				
    				// Update sub-assignment
    				subAssignment.setName(subAssignmentNames.get(i));
    				subAssignment.setWeight(subAssignmentWeights.get(i));
    			}
    		}
    		else if(subAssignmentNames.size() < parentTemplate.getNumSubAssignments())  // Case 2
    		{
    			// Just need to update the names and weights that we have entries for
    			for(int i = 0; i < subAssignmentNames.size(); i++)
    			{
    				// Get sub-assignment for both entry and template
    				// Can cast because sub-assignment field size > 0, so have at least one
    				RealAssignment subAssignment = (RealAssignment) entryAssignment.getSubAssignment(i);
    				
    				// Update sub-assignment
    				subAssignment.setName(subAssignmentNames.get(i));
    				subAssignment.setWeight(subAssignmentWeights.get(i));
    			}
    		}
    		else  // Case 3
    		{
    			// Just need to update names and weights of sub-assignments
    			for(int i = 0; i < entryAssignment.getNumSubAssignments(); i++)
    			{
    				// Get sub-assignment
    				// Can cast because checked that numSubAssignments != 0 above
    				RealAssignment subAssignment = (RealAssignment) entryAssignment.getSubAssignment(i);
    				
    				// Update sub-assignment
    				subAssignment.setName(subAssignmentNames.get(i));
    				subAssignment.setWeight(subAssignmentWeights.get(i));
    			}
    		}
    	}
    }

}
