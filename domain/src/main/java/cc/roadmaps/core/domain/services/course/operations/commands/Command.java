package cc.roadmaps.core.domain.services.course.operations.commands;

import cc.roadmaps.core.domain.services.courseAffiliation.AffiliationResolvable;

public interface Command extends AffiliationResolvable {

    CommandType getCommandType();
}
