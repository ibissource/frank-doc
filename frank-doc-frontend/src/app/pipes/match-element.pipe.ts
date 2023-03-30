import { Pipe, PipeTransform } from '@angular/core';
import { AppService } from '../app.service';
import { Elements, Element } from '../app.types';
import { Group } from '../frankdoc.types';

@Pipe({
  name: 'matchElement'
})
export class MatchElementPipe implements PipeTransform {

  constructor(private appService: AppService) {}

  transform(elements: Elements, searchText?: string, group?: Group){
    if (!elements || !group) return {}; //Cannot filter elements if no group has been selected

    const returnELements: Elements = {},
      matchedParents: { [index: string]: boolean } = {}, // cache matched parents
      noMatchParents: { [index: string]: boolean } = {}, // cache no match parents
      groupMembers = this.appService.getGroupElements(group.types),
      searchTextLC = searchText && searchText != "" ? searchText.toLowerCase() : undefined;

    for (const elementName of groupMembers){
      const element = elements[elementName];

      if (!searchTextLC) {
        returnELements[elementName] = element;
        continue;
      }
      if (this.elementToJSON(element).includes(searchTextLC)) {
        returnELements[elementName] = element;
        continue;
      }

      // search in parent (accessing children is an expensive operation)
      const parentStack = [];
      let elementParentName = elements[elementName].parent;
      while (elementParentName) {
        parentStack.push(elementParentName); // keep list of unmatched parents

        if (noMatchParents[elementParentName]) break;
        if (matchedParents[elementParentName]) {
          returnELements[elementName] = element;
          break;
        }

        const parentElement = elements[elementParentName];

        if (this.elementToJSON(parentElement).includes(searchTextLC)) {
          returnELements[elementName] = element;
          matchedParents[elementParentName] = true;
          break;
        }
        if (!parentElement.parent) {
          for (const t of parentStack) {
            noMatchParents[t] = true;
          }
          break;
        }
        elementParentName = parentElement.parent;
      }
    }

    return returnELements;
  }

  elementToJSON(element: Element) {
    return JSON.stringify(element).replace(/"/g, '').toLowerCase();
  }

}
