/*
 * This file is part of Almura, All Rights Reserved.
 *
 * Copyright (c) AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almura.content;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.util.ResettableBuilder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A "Pack" acts as a {@link CatalogType} whose goal is to be a container of catalog types.
 */
public class Pack implements CatalogType {

    private final String id;
    private final String name;
    private final Map<String, CatalogType> objectsById;

    Pack(String id, String name, Map<String, CatalogType> objectsById) {
        this.id = id;
        this.name = name;
        this.objectsById = objectsById;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Set<BlockType> getBlocks() {
        return this.objectsById.values().stream().filter(en -> en instanceof BlockType).map(en -> (BlockType) en).collect(Collectors
                .toSet());
    }

    public Set<ItemType> getItems() {
        return this.objectsById.values().stream().filter(en -> en instanceof ItemType).map(en -> (ItemType) en).collect(Collectors
                .toSet());
    }

    public void add(CatalogType object) {
        checkNotNull(object);
        checkArgument(!this.objectsById.containsValue(object), "Attempt to double add catalog type to pack!");
    }

    public Set<CatalogType> getObjects() {
        return this.objectsById.values().stream().collect(Collectors.toSet());
    }

    public static final class Builder implements ResettableBuilder<Pack, Builder> {

        private final Map<String, CatalogType> objectsById = new LinkedHashMap<>();

        public Builder object(CatalogType object) {
            checkNotNull(object);
            checkArgument(!this.objectsById.containsValue(object), "Attempt to double add catalog type to builder!");

            this.objectsById.put(object.getId(), object);
            return this;
        }

        @Override
        public Builder from(Pack value) {
            throw new UnsupportedOperationException("Building a Pack from a Pack is unsupported!");
        }

        @Override
        public Builder reset() {
            this.objectsById.clear();
            return this;
        }

        public Pack build(String id, String name) {
            checkNotNull(id);
            checkState(!id.isEmpty(), "Id cannot be empty!");
            checkNotNull(name);
            checkState(!name.isEmpty(), "Name cannot be empty!");

            return new Pack(id, name, objectsById);
        }
    }
}
