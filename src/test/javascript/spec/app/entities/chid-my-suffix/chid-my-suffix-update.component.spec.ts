/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ChidrenTestModule } from '../../../test.module';
import { ChidMySuffixUpdateComponent } from 'app/entities/chid-my-suffix/chid-my-suffix-update.component';
import { ChidMySuffixService } from 'app/entities/chid-my-suffix/chid-my-suffix.service';
import { ChidMySuffix } from 'app/shared/model/chid-my-suffix.model';

describe('Component Tests', () => {
    describe('ChidMySuffix Management Update Component', () => {
        let comp: ChidMySuffixUpdateComponent;
        let fixture: ComponentFixture<ChidMySuffixUpdateComponent>;
        let service: ChidMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ChidrenTestModule],
                declarations: [ChidMySuffixUpdateComponent]
            })
                .overrideTemplate(ChidMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ChidMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChidMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ChidMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.chid = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ChidMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.chid = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
