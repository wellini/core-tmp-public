package cc.roadmaps.core.service.integrations.web.rest.api.leaf.dtos;

import cc.roadmaps.core.domain.model.leaf.Leaf;
import cc.roadmaps.core.domain.model.leaf.LinkLeaf;
import cc.roadmaps.core.domain.model.leaf.TextLeaf;
import cc.roadmaps.core.domain.model.leaf.enums.LeafType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GetLeafResponse {

    @Schema(required = true)
    private UUID id;

    @Schema(required = true)
    private String title;

    @Schema(required = true)
    private LeafType type;

    @Schema(required = false)
    private Integer orderId;

    @Schema(required = true, oneOf = {TextLeafData.class, LinkLeafData.class})
    private LeafData data;

    @Schema(required = true)
    private UUID moduleId;

    private GetLeafResponse(UUID id, String title, LeafType type, Integer orderId, LeafData data, UUID moduleId) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.orderId = orderId;
        this.data = data;
        this.moduleId = moduleId;
    }

    private static GetLeafResponse create(LinkLeaf leaf, Integer orderId) {
        return new GetLeafResponse(
                leaf.getId(),
                leaf.getTitle(),
                leaf.getType(),
                orderId,
                LinkLeafData.create(leaf),
                leaf.getModuleId()
        );
    }

    private static GetLeafResponse create(TextLeaf leaf, Integer orderId) {
        return new GetLeafResponse(
                leaf.getId(),
                leaf.getTitle(),
                leaf.getType(),
                orderId,
                TextLeafData.create(leaf),
                leaf.getModuleId()
        );
    }

    public static <T extends Leaf> GetLeafResponse create(T leaf, Integer orderId) {
        if (leaf instanceof TextLeaf) {
            return create((TextLeaf) leaf, orderId);
        } else if (leaf instanceof LinkLeaf) {
            return create((LinkLeaf) leaf, orderId);
        }
        throw new IllegalArgumentException("No compatible response dto for passed leaf class type");
    }

    public static <T extends Leaf> GetLeafResponse create(T leaf) {
        return create(leaf, null);
    }

    abstract static class LeafData {}

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class TextLeafData extends LeafData {

        @Schema(required = false)
        private String text;

        public static TextLeafData create(TextLeaf textLeaf) {
            return new TextLeafData(textLeaf.getText());
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class LinkLeafData extends LeafData {

        @Schema(required = true)
        private String link;

        public static LinkLeafData create(LinkLeaf linkLeaf) {
            return new LinkLeafData(linkLeaf.getLink());
        }
    }
}
