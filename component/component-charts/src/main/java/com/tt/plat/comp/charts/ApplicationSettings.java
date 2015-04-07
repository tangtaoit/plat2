package com.tt.plat.comp.charts;

import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.request.resource.PackageResourceReference;

/**
 * Created by tangtao on 2014/9/13.
 * Desc:
 */
public class ApplicationSettings {
    private static final MetaDataKey<ApplicationSettings> KEY = new MetaDataKey<ApplicationSettings>() {
    };

    private PackageResourceReference jqueryTimeFlotpackageResourceReference = new PackageResourceReference(FlotPanel.class,"jquery.flot.time.min.js");

    private  PackageResourceReference jqueryFlotpackageResourceReference = new PackageResourceReference(FlotPanel.class,"jquery.flot.min.js");

    private  PackageResourceReference cssFlotpackageResourceReference = new PackageResourceReference(FlotPanel.class,"FlotPanel.css");

    private  PackageResourceReference jqueryFlotResizepackageResourceReference = new PackageResourceReference(FlotPanel.class,"jquery.flot.resize.min.js");

    private  PackageResourceReference jqueryFlotToolTippackageResourceReference = new PackageResourceReference(FlotPanel.class,"jquery.flot.tooltip.min.js");

    private boolean includeJqueryTime=true;

    private boolean includeJqueryFlot=true;

    private boolean includeFlotCss = true;

    private boolean includeFlotResize=true;

    private boolean includeFlotToolTip=true;


    public boolean isIncludeJqueryTime() {
        return includeJqueryTime;
    }

    public void setIncludeJqueryTime(boolean includeJqueryTime) {
        this.includeJqueryTime = includeJqueryTime;
    }

    public boolean isIncludeJqueryFlot() {
        return includeJqueryFlot;
    }

    public void setIncludeJqueryFlot(boolean includeJqueryFlot) {
        this.includeJqueryFlot = includeJqueryFlot;
    }

    public boolean isIncludeFlotCss() {
        return includeFlotCss;
    }

    public void setIncludeFlotCss(boolean includeFlotCss) {
        this.includeFlotCss = includeFlotCss;
    }

    public PackageResourceReference getJqueryTimeFlotpackageResourceReference() {
        return jqueryTimeFlotpackageResourceReference;
    }

    public void setJqueryTimeFlotpackageResourceReference(PackageResourceReference jqueryTimeFlotpackageResourceReference) {
        this.jqueryTimeFlotpackageResourceReference = jqueryTimeFlotpackageResourceReference;
    }

    public PackageResourceReference getJqueryFlotpackageResourceReference() {
        return jqueryFlotpackageResourceReference;
    }

    public void setJqueryFlotpackageResourceReference(PackageResourceReference jqueryFlotpackageResourceReference) {
        this.jqueryFlotpackageResourceReference = jqueryFlotpackageResourceReference;
    }

    public PackageResourceReference getCssFlotpackageResourceReference() {
        return cssFlotpackageResourceReference;
    }

    public void setCssFlotpackageResourceReference(PackageResourceReference cssFlotpackageResourceReference) {
        this.cssFlotpackageResourceReference = cssFlotpackageResourceReference;
    }

    public boolean isIncludeFlotResize() {
        return includeFlotResize;
    }

    public void setIncludeFlotResize(boolean includeFlotResize) {
        this.includeFlotResize = includeFlotResize;
    }

    public PackageResourceReference getJqueryFlotResizepackageResourceReference() {
        return jqueryFlotResizepackageResourceReference;
    }

    public void setJqueryFlotResizepackageResourceReference(PackageResourceReference jqueryFlotResizepackageResourceReference) {
        this.jqueryFlotResizepackageResourceReference = jqueryFlotResizepackageResourceReference;
    }

    public PackageResourceReference getJqueryFlotToolTippackageResourceReference() {
        return jqueryFlotToolTippackageResourceReference;
    }

    public void setJqueryFlotToolTippackageResourceReference(PackageResourceReference jqueryFlotToolTippackageResourceReference) {
        this.jqueryFlotToolTippackageResourceReference = jqueryFlotToolTippackageResourceReference;
    }

    public boolean isIncludeFlotToolTip() {
        return includeFlotToolTip;
    }

    public void setIncludeFlotToolTip(boolean includeFlotToolTip) {
        this.includeFlotToolTip = includeFlotToolTip;
    }

    public static ApplicationSettings get() {
        // FIXME Application should provide setMetadataIfAbsent()
        Application application = Application.get();
        ApplicationSettings settings = application.getMetaData(KEY);
        if (settings == null) {
            synchronized (application) {
                settings = application.getMetaData(KEY);
                if (settings == null) {
                    settings = new ApplicationSettings();
                    application.setMetaData(KEY, settings);
                }
            }
        }
        return application.getMetaData(KEY);
    }
}
