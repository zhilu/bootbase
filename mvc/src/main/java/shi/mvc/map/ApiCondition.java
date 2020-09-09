package shi.mvc.map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

public class ApiCondition implements RequestCondition<ApiCondition> {

    private ApiItem version;

    public ApiCondition(ApiItem version) {
        this.version = version;
    }

    @Override
    public ApiCondition combine(ApiCondition other) {
        // 选择版本最大的接口
        return version.compareTo(other.version) >= 0 ? new ApiCondition(version) : new ApiCondition(other.version);
    }

    @Override
    public ApiCondition getMatchingCondition(HttpServletRequest request) {
        String version = request.getHeader("x-api");
        ApiItem item = ApiConverter.convert(version);
        // 获取所有小于等于版本的接口
        if (item.compareTo(this.version) >= 0) {
            return this;
        }

        return null;
    }

    @Override
    public int compareTo(ApiCondition other, HttpServletRequest request) {
        // 获取最大版本对应的接口
        return other.version.compareTo(this.version);
    }
}
