package com.androidproject.models.Laureate;

import java.util.List;

public class LaureateSkill {
    private List<LaureateSkillLinguistics> laureateSkillLinguistics;
    private List<LaureateSkilltechnical> laureateSkilltechnicals;

    public LaureateSkill(List<LaureateSkillLinguistics> laureateSkillLinguistics, List<LaureateSkilltechnical> laureateSkilltechnicals) {
        this.laureateSkillLinguistics = laureateSkillLinguistics;
        this.laureateSkilltechnicals = laureateSkilltechnicals;
    }

    public List<LaureateSkillLinguistics> getLaureateSkillLinguistics() {
        return laureateSkillLinguistics;
    }

    public void setLaureateSkillLinguistics(List<LaureateSkillLinguistics> laureateSkillLinguistics) {
        this.laureateSkillLinguistics = laureateSkillLinguistics;
    }

    public List<LaureateSkilltechnical> getLaureateSkilltechnicals() {
        return laureateSkilltechnicals;
    }

    public void setLaureateSkilltechnicals(List<LaureateSkilltechnical> laureateSkilltechnicals) {
        this.laureateSkilltechnicals = laureateSkilltechnicals;
    }
}
