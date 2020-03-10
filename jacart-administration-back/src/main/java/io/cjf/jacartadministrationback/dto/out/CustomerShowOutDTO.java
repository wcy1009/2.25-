package io.cjf.jacartadministrationback.dto.out;

public class CustomerShowOutDTO {
    private Integer customerId;
    private String username;
    private String realName;
    private String mobile;
    private String email;
    private Byte status;
    private Long createTimestamp;
    private Boolean newSubscribed;
    private Integer rewordPoints;
    private Integer defaultAddressId;
    private String AvatarUrl;
    private Boolean  NewsSubscribed;
    private  String DefaultAddress;

    public String getDefaultAddress() {
        return DefaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        DefaultAddress = defaultAddress;
    }

    public String getAvatarUrl() {
        return AvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        AvatarUrl = avatarUrl;
    }

    public Boolean getNewsSubscribed() {
        return NewsSubscribed;
    }

    public void setNewsSubscribed(Boolean newsSubscribed) {
        NewsSubscribed = newsSubscribed;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Boolean getNewSubscribed() {
        return newSubscribed;
    }

    public void setNewSubscribed(Boolean newSubscribed) {
        this.newSubscribed = newSubscribed;
    }

    public Integer getRewordPoints() {
        return rewordPoints;
    }

    public void setRewordPoints(Integer rewordPoints) {
        this.rewordPoints = rewordPoints;
    }

    public Integer getDefaultAddressId() {
        return defaultAddressId;
    }

    public void setDefaultAddressId(Integer defaultAddressId) {
        this.defaultAddressId = defaultAddressId;
    }
}
