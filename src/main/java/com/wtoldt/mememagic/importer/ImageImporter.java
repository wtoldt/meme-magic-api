package com.wtoldt.mememagic.importer;

import com.wtoldt.mememagic.domain.Image;

import java.net.URI;
import java.util.Set;

/**
 * Image Importer interface to import images from any source
 * Created by Emily Li on 04/11/2017.
 */
public interface ImageImporter {
    Set<Image> getImages(URI imageGalleryUrls);
}
