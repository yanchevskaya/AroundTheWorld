package controller.giveInfo.travellers;

import model.Traveller;
import tags.bean.TravellerCollection;

import java.util.List;

 class TakeTravellerList {
    /**
     * receive information about travellers for one required page
     * @param pageStart - position in the list from what we need ro receive information
     * @param traveller - list of travellers
     * @return bean of Travellers to print it
     */
     TravellerCollection takeTravellers(int total, int pageStart, List<Traveller> traveller) {
        int pageEnd;
        int count=0;
        if (pageStart!=0)
            pageStart = (pageStart - 1) * total;
        pageEnd = pageStart + total;

        if (traveller == null)
            //noinspection ConstantConditions
            return new TravellerCollection(traveller, count);

        if (traveller.size() < pageEnd)
            pageEnd = traveller.size();
        List <Traveller> shortList = traveller.subList(pageStart, pageEnd);

        if (traveller.size()>total)
        count = traveller.size() % total != 0 ? traveller.size() / total + 1 : traveller.size() / total;

        return new TravellerCollection(shortList, count);
    }
}

