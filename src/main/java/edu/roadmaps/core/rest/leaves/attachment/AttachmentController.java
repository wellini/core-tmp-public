package edu.roadmaps.core.rest.leaves.attachment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class AttachmentController {

    @PostMapping("/v0.1/courses/modules/{module_id}/lectures/{lecture_id}/attachments")
    public ResponseEntity<?> createAttachment(@PathVariable Long moduleId,
                                              @PathVariable Long lectureId){
        return null;
    }

    @PostMapping("/v0.1/courses/modules/{module_id}/lectures/{lecture_id}/attachments/{attach_id}")
    public ResponseEntity<?> deleteAttachment(@PathVariable Long moduleId,
                                              @PathVariable Long lectureId,
                                              @PathVariable Long attachId){
        return null;
    }

}
